package com.example.homeworkme.repository

import com.example.homeworkme.dto.GetHomeWorkResponse
import com.example.homeworkme.properties.DnevnikruProperties
import feign.RequestInterceptor
import org.apache.http.NameValuePair
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.message.BasicNameValuePair
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.atomic.AtomicReference
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE as JSON

@FeignClient(
    value = "dnevnikru-api",
    url = "\${bot.dnevnikru.url}",
    configuration = [DnevnikruClientConfiguration::class]
)
interface DnevnikruClient {
    @GetMapping(
        "/api/userfeed/persons/1000012922676/schools/48944/groups/1854336673520927490/schedule?date={timestamp}&takeDays=1",
        produces = [JSON]
    )
    fun getHomework(@PathVariable timestamp: Long): GetHomeWorkResponse
}

@EnableConfigurationProperties(DnevnikruProperties::class)
class DnevnikruClientConfiguration(
    private val cookieProvider: AuthProvider,
    private val properties: DnevnikruProperties
) {

    @Bean
    fun dnevnikruClientRequestInterceptor(): RequestInterceptor = RequestInterceptor { template ->
        template.header(HttpHeaders.ACCEPT, JSON)
        // template.header(HttpHeaders.COOKIE, properties.token)
        template.header(HttpHeaders.USER_AGENT, properties.userAgent)
    }

}

@Component
@EnableConfigurationProperties(DnevnikruProperties::class)
class AuthProvider(
    private val httpClient: HttpClient,
    private val properties: DnevnikruProperties,
) {
    private val log = LoggerFactory.getLogger(AuthProvider::class.java)

    private val token: AtomicReference<String?> = AtomicReference()

    fun getToken(): String? = token.get() ?: token.updateAndGet {
        login()
    }

    private fun login() = runCatching {
        HttpPost(properties.loginUrl).apply {
            setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE)
            setHeader(HttpHeaders.USER_AGENT, properties.userAgent)
            setEntity(
                UrlEncodedFormEntity(
                    listOf(
                        BasicNameValuePair("login", properties.login),
                        BasicNameValuePair("password", properties.pass),
                    )
                )
            )
        }.run {
            httpClient.execute(this)
        }.run {
            // todo
        }
    }.onFailure { error ->
        log.error("Cannot login. error: {}", error)
    }
}

package com.example.homeworkme.repository

import com.example.homeworkme.dto.GetHomeWorkResponse
import com.example.homeworkme.properties.DnevnikruProperties
import feign.RequestInterceptor
import org.apache.http.client.HttpClient
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.impl.client.BasicCookieStore
import org.apache.http.message.BasicNameValuePair
import org.slf4j.LoggerFactory
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

class DnevnikruClientConfiguration(
    private val authProvider: AuthProvider,
    private val properties: DnevnikruProperties
) {
    @Bean
    fun dnevnikruClientRequestInterceptor(): RequestInterceptor = RequestInterceptor { template ->
        val token = authProvider.getToken()
        template.header(HttpHeaders.ACCEPT, JSON)
        template.header(HttpHeaders.COOKIE, token)
        template.header(HttpHeaders.USER_AGENT, properties.userAgent)
    }

}

@Component
class AuthProvider(
    private val httpClient: HttpClient,
    private val properties: DnevnikruProperties,
) {
    private val log = LoggerFactory.getLogger(AuthProvider::class.java)

    fun getToken(): String = runCatching {
        val post = HttpPost(properties.loginUrl).apply {
            setHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_FORM_URLENCODED_VALUE)
            setHeader(HttpHeaders.USER_AGENT, properties.userAgent)
            entity = UrlEncodedFormEntity(
                listOf(
                    BasicNameValuePair("login", properties.login),
                    BasicNameValuePair("password", properties.pass),
                )
            )
        }

        val context = HttpClientContext.create()
        context.setAttribute(HttpClientContext.COOKIE_STORE, BasicCookieStore())

        httpClient.execute(post, context)

        context.cookieStore.cookies.firstOrNull { it.name == NAME }?.value?.let {
            "$NAME=$it"
        }.also {
            log.debug("token: $it")
        }
    }.onFailure { error ->
        log.error("Login error: {}", error)
        throw error
    }.getOrNull()!!

    companion object {
        private const val NAME = "DnevnikAuth_a"
    }
}

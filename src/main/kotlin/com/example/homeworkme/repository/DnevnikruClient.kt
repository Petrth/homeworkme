package com.example.homeworkme.repository

import com.example.homeworkme.model.HomeworkDay
import com.example.homeworkme.properties.DnevnikruProperties
import feign.RequestInterceptor
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE as JSON

@FeignClient(
    value = "dnevnikru-api",
    url = "\${bot.dnevnikru.url}",
    configuration = [DnevnikruClientConfiguration::class]
)
interface DnevnikruClient {
    @GetMapping("/api/userfeed/persons/1000012922676/schools/48944/groups/1854336673520927490/schedule?date={timestamp}&takeDays=1")
    fun getHomework(@PathVariable timestamp: Long): List<HomeworkDay>
}

@EnableConfigurationProperties(DnevnikruProperties::class)
class DnevnikruClientConfiguration(private val properties: DnevnikruProperties) {

    @Bean
    fun dnevnikruClientRequestInterceptor(): RequestInterceptor = RequestInterceptor { template ->
        template.header(HttpHeaders.ACCEPT, JSON)
        template.header(HttpHeaders.COOKIE, properties.token)
        template.header(HttpHeaders.USER_AGENT, properties.userAgent)
    }

}

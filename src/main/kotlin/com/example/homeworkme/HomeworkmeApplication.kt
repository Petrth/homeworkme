package com.example.homeworkme

import com.example.homeworkme.properties.DnevnikruProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableCaching
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(DnevnikruProperties::class)
class HomeworkmeApplication

fun main(args: Array<String>) {
    runApplication<HomeworkmeApplication>(*args)
}

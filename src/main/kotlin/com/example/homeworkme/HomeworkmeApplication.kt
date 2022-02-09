package com.example.homeworkme

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@EnableFeignClients
@EnableScheduling
@SpringBootApplication
class HomeworkmeApplication

fun main(args: Array<String>) {
    runApplication<HomeworkmeApplication>(*args)
}

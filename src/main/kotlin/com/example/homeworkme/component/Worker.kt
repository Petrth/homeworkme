package com.example.homeworkme.component

import com.example.homeworkme.repository.DnevnikruClient
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class Worker(
    private val dnevnikruClient: DnevnikruClient
) {

    // @Scheduled(cron = "\${bot.dnevnikru.cron}")
    fun getHomework() {
        val timestamp = Instant.now().truncatedTo(ChronoUnit.DAYS).toEpochMilli()/1000
        val homeworkDays = dnevnikruClient.getHomework(timestamp).firstOrNull()

    }
}
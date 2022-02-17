package com.example.homeworkme.component

import com.example.homeworkme.repository.DnevnikruClient
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class Worker(
    private val dnevnikruClient: DnevnikruClient,
    private val homework: MutableList<Pair<String, String>>
) {

    @Scheduled(cron = "\${bot.dnevnikru.cron}")
    fun getHomework() {
        val timestamp = Instant.now().truncatedTo(ChronoUnit.DAYS).toEpochMilli() / 1000
        val homeworkDay = dnevnikruClient.getHomework(timestamp).days.firstOrNull()

        homeworkDay?.lessons?.map {
            it.subject.name to it.homework.text
        }?.let {
            homework.clear()
            homework.addAll(it)
        }
    }
}
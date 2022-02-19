package com.example.homeworkme.service

import com.example.homeworkme.model.HomeworkDay
import com.example.homeworkme.repository.DnevnikruClient
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.Instant
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit.DAYS

@Service
class HomeworkService(
    private val dnevnikruClient: DnevnikruClient
) {
    private val log = LoggerFactory.getLogger(HomeworkService::class.java)

    @Cacheable("homework")
    fun getHomework(): Pair<Instant, List<HomeworkDay>> {
        val date = getDate()
        val timestamp = date.toEpochMilli() / 1000
        log.info("get homework for date: {}", date)
        return date to dnevnikruClient.getHomework(timestamp).days
    }

    private fun getDate(): Instant {
        val date = Instant.now().truncatedTo(DAYS).plus(1, DAYS)
        val offsetDateTime = date.atOffset(ZoneOffset.UTC)
        return when (offsetDateTime.dayOfWeek) {
            DayOfWeek.SATURDAY -> date.plus(2, DAYS)
            DayOfWeek.SUNDAY -> date.plus(1, DAYS)
            else -> date
        }
    }
}
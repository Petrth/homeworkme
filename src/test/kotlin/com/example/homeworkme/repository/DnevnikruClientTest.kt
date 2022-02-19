package com.example.homeworkme.repository

import com.example.homeworkme.AbstractBotTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.Instant
import java.time.temporal.ChronoUnit.DAYS

@Disabled
class DnevnikruClientTest : AbstractBotTest() {
    @Autowired
    private lateinit var dnevnikruClient: DnevnikruClient

    @Test
    fun `getHomework returns expected json`() {
        val timestamp = Instant.now().truncatedTo(DAYS).minus(2, DAYS).toEpochMilli() / 1000

        val response = dnevnikruClient.getHomework(timestamp)

        println(response)
    }
}
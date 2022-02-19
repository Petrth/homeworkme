package com.example.homeworkme.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration

@ConstructorBinding
@ConfigurationProperties(prefix = "bot")
data class BotProperties(
    val username: String,
    val token: String,
    val ttl: Duration
)
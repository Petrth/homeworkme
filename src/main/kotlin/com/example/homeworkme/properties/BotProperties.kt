package com.example.homeworkme.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

@ConstructorBinding
@ConfigurationProperties(prefix = "bot")
data class BotProperties(
    val username: String = "",
    val token: String = ""
)
package com.example.homeworkme.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.net.URI

@ConstructorBinding
@ConfigurationProperties(prefix = "bot.dnevnikru")
data class DnevnikruProperties(
    val token: String,

    val userAgent: String,

    val loginUrl: URI,

    val login: String,

    val pass: String,
)
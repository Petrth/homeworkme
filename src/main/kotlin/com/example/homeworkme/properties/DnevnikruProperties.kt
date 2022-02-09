package com.example.homeworkme.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.validation.constraints.NotEmpty

//@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "bot.dnevnikru")
data class DnevnikruProperties(
    @NotEmpty
    val token: String = "",

    @NotEmpty
    val userAgent: String = "",


)
package com.example.homeworkme.config

import com.example.homeworkme.model.BotCommand
import com.example.homeworkme.service.ReceiverService
import org.apache.http.impl.client.HttpClients
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.extensions.bots.commandbot.commands.DefaultBotCommand
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender


@Configuration
class BotConfig {

    @Bean
    fun httpClient() = HttpClients.createDefault()!!

    @Bean
    fun botCommands(): List<IBotCommand> = listOf(
        MyDefaultBotCommand()
    )
}

class MyDefaultBotCommand() : DefaultBotCommand(BotCommand.BUTTON.id, BotCommand.BUTTON.desc) {
    private val log = LoggerFactory.getLogger(ReceiverService::class.java)

    override fun execute(
        absSender: AbsSender?,
        user: User?,
        chat: Chat?,
        messageId: Int?,
        arguments: Array<out String>?
    ) {
        log.info("execute: {} {} {} {} {}", absSender, user, chat, messageId, arguments)
    }
}

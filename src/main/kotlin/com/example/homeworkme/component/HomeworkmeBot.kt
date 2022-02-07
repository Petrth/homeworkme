package com.example.homeworkme.component

import com.example.homeworkme.properties.BotProperties
import com.example.homeworkme.service.ReceiverService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import javax.annotation.PostConstruct

@Component
@EnableConfigurationProperties(BotProperties::class)
class HomeworkmeBot(
    private val botProperties: BotProperties,
    private val botCommands: List<IBotCommand>,
    private val receiverService: ReceiverService
) : TelegramLongPollingCommandBot() {

    @PostConstruct
    fun init() {
        botCommands.forEach {
            register(it)
        }

        registerDefaultAction { absSender, message ->

            val sendMessage = SendMessage()
            sendMessage.chatId = message.chatId.toString()
            sendMessage.text = "Command '" + message.text.toString() + "' unknown"

            absSender.execute(sendMessage)
        }
    }

    override fun getBotUsername() = botProperties.username

    override fun getBotToken() = botProperties.token

    override fun processNonCommandUpdate(update: Update) {
        receiverService.receive(update)
    }


}
package com.example.homeworkme

import com.example.homeworkme.properties.BotProperties
import com.example.homeworkme.service.ReceiverService
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import javax.annotation.PostConstruct


@Component
@EnableConfigurationProperties(BotProperties::class)
class HomeworkmeBot(
    private val botProperties: BotProperties,
    private val botCommands: List<IBotCommand>,
    private val receiverService: ReceiverService
) : TelegramLongPollingCommandBot() {
    private val log = LoggerFactory.getLogger(HomeworkmeBot::class.java)

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
        val chatId = update.message?.chatId ?: update.callbackQuery?.message?.chatId
        execute(buildInlineKeyboardSendMessage(chatId ?: log.error("chatId is null").run { return }))
    }

    fun buildInlineKeyboardSendMessage(chatId: Long): SendMessage = SendMessage().apply {
        this.chatId = chatId.toString()
        text = "Привед 5А (тут будет ДЗ...)"
        replyMarkup = InlineKeyboardMarkup().apply {
            keyboard = listOf(
                listOf(
                    InlineKeyboardButton().apply {
                        text = "Дай домашку"
                        callbackData = "button has been pressed"
                    })
            )
        }
    }
}

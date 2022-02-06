package com.example.homeworkme.service

import org.slf4j.LoggerFactory.*
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class ReceiverService() {
    private val log = getLogger(ReceiverService::class.java)

    fun receive(update: Update) {
        when {
            update.hasCallbackQuery() -> {
                log.info("receive(): callbackQuery: {}", update.callbackQuery)
            }
            update.hasMessage() -> {
                log.info("receive(): message: {}", update.message)
            }
            else -> {
                log.info("receive(): not supported: {}", update)
            }
        }
    }

}

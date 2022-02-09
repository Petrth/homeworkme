package com.example.homeworkme.model

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LessonTest {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun serializeTest() {
        val lesson = Lesson(Subject("xxx"), Homework("homework42"))
        val actual = objectMapper.writeValueAsString(lesson)
        assertEquals("", actual)
    }
}
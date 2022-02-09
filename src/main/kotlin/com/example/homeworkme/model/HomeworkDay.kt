package com.example.homeworkme.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class HomeworkDay(
    val lessons: List<Lesson>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Lesson(
    val subject: Subject,
    val homework: Homework,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Subject(
    val name: String = ""
)

data class Homework(
    val text: String = ""
)



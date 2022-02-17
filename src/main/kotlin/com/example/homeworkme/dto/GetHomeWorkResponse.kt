package com.example.homeworkme.dto

import com.example.homeworkme.model.HomeworkDay

data class GetHomeWorkResponse(
    val days: List<HomeworkDay>
)
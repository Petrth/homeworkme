package com.example.homeworkme.dto

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GetHomeWorkResponseTest {

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `deserialize test`() {
        val given =
            """
            {
              "days": [
                {
                  "date": "1644364800",
                  "utcOffset": 180,
                  "hasImportantWork": false,
                  "dayHomeworksProgress": {
                    "totalLessonsWithHomeworksCount": 6,
                    "completedLessonsWithHomeworksCount": 0
                  },
                  "lessons": [
                    {
                      "id": "1899183270326769436",
                      "number": 1,
                      "place": "3",
                      "startTime": "1644384600",
                      "endTime": "1644387300",
                      "hours": {
                        "startHour": "8",
                        "startMinute": "30",
                        "endHour": "9",
                        "endMinute": "15"
                      },
                      "isCanceled": false,
                      "theme": "Обобщающий урок по теме «Земля во Вселенной»",
                      "meetingUrl": null,
                      "subject": {
                        "id": "1718267151494665",
                        "name": "География",
                        "knowledgeArea": "SocialStudies"
                      },
                      "importantWorks": [],
                      "homework": {
                        "text": "П. 15 ТПО стр. 38-39; https://api-edu.skysmart.ru/api/v1/dnevnikru/homework?taskHash=bivahinuzi; https://api-edu.skysmart.ru/api/v1/dnevnikru/homework?taskHash=talesivano",
                        "isCompleted": false,
                        "workIsAttachRequired": false,
                        "attachments": [],
                        "userAttachments": [],
                        "lessonId": null
                      },
                      "hasAttachment": false,
                      "workMarks": [
                        {
                          "workId": "1910591042767384852",
                          "marks": [
                            {
                              "id": "1912172612934515763",
                              "value": "5",
                              "mood": "Good"
                            }
                          ]
                        }
                      ],
                      "isEmpty": false,
                      "comment": null
                    },
                    {
                      "id": "1899183270326769443",
                      "number": 2,
                      "place": "3",
                      "startTime": "1644387900",
                      "endTime": "1644390600",
                      "hours": {
                        "startHour": "9",
                        "startMinute": "25",
                        "endHour": "10",
                        "endMinute": "10"
                      },
                      "isCanceled": false,
                      "theme": "Задачи для самопроверки",
                      "meetingUrl": null,
                      "subject": {
                        "id": "1718318691102224",
                        "name": "Математика",
                        "knowledgeArea": "Mathematics"
                      },
                      "importantWorks": [],
                      "homework": {
                        "text": "№ 342, 344(2), 352, 353",
                        "isCompleted": false,
                        "workIsAttachRequired": false,
                        "attachments": [],
                        "userAttachments": [],
                        "lessonId": null
                      },
                      "hasAttachment": false,
                      "workMarks": [],
                      "isEmpty": false,
                      "comment": null
                    },
                    {
                      "id": "1899183270326769450",
                      "number": 3,
                      "place": "3",
                      "startTime": "1644391800",
                      "endTime": "1644394500",
                      "hours": {
                        "startHour": "10",
                        "startMinute": "30",
                        "endHour": "11",
                        "endMinute": "15"
                      },
                      "isCanceled": false,
                      "theme": "Склонение имён существительных. Падеж.",
                      "meetingUrl": null,
                      "subject": {
                        "id": "1718370230709785",
                        "name": "Рус. язык",
                        "knowledgeArea": "Philology"
                      },
                      "importantWorks": [],
                      "homework": {
                        "text": "п. 38, 39, составить предложения со словами из орфографического практикума (стр. 16), выполнить фонетический разбор слов: сладкоежка, обстановка.",
                        "isCompleted": false,
                        "workIsAttachRequired": false,
                        "attachments": [],
                        "userAttachments": [],
                        "lessonId": null
                      },
                      "hasAttachment": false,
                      "workMarks": [
                        {
                          "workId": "1912549822732246755",
                          "marks": [
                            {
                              "id": "1912550033185656446",
                              "value": "2",
                              "mood": "Bad"
                            }
                          ]
                        }
                      ],
                      "isEmpty": false,
                      "comment": null
                    },
                    {
                      "id": "1899183270326769457",
                      "number": 4,
                      "place": "3",
                      "startTime": "1644395100",
                      "endTime": "1644397800",
                      "hours": {
                        "startHour": "11",
                        "startMinute": "25",
                        "endHour": "12",
                        "endMinute": "10"
                      },
                      "isCanceled": false,
                      "theme": "В городе богини Афины ",
                      "meetingUrl": null,
                      "subject": {
                        "id": "1718310101167631",
                        "name": "История",
                        "knowledgeArea": "SocialStudies"
                      },
                      "importantWorks": [],
                      "homework": {
                        "text": "Параграф 34,35\nСтр. 170 работа с датами. \nСтр. 170. Составьте рассказ об одном из сражений греков с персами от имени участника этого сражения",
                        "isCompleted": false,
                        "workIsAttachRequired": false,
                        "attachments": [],
                        "userAttachments": [],
                        "lessonId": null
                      },
                      "hasAttachment": false,
                      "workMarks": [],
                      "isEmpty": false,
                      "comment": null
                    },
                    {
                      "id": "1899183270326769464",
                      "number": 5,
                      "place": "Спортзал",
                      "startTime": "1644399000",
                      "endTime": "1644401700",
                      "hours": {
                        "startHour": "12",
                        "startMinute": "30",
                        "endHour": "13",
                        "endMinute": "15"
                      },
                      "isCanceled": false,
                      "theme": "",
                      "meetingUrl": null,
                      "subject": {
                        "id": "1718383115611675",
                        "name": "Физкультура",
                        "knowledgeArea": "PhysicalCulture"
                      },
                      "importantWorks": [],
                      "homework": {
                        "text": "Лежа на спине поднимая туловище коснуться руками носков - 18р (м), 14р (д) Лежа на спине поднимание прямых ног - 18 р (м), 14 р (д). Сед углом: разведение ног   в стороны и сведение; сгибание ног, подтягивание коленей к груди; разведение и сведение ног со скрещениями - 1х15р (м), 1x12 (д) Лежа на животе отрывая ноги и руки от пола выполнить (лодочку) - 14р (м),      12р (д) Лежа на спине, ноги влево поднимая ноги перевести вправо (часики) - 14р (м),    12 р (д). ",
                        "isCompleted": false,
                        "workIsAttachRequired": false,
                        "attachments": [],
                        "userAttachments": [],
                        "lessonId": null
                      },
                      "hasAttachment": false,
                      "workMarks": [],
                      "isEmpty": false,
                      "comment": null
                    },
                    {
                      "id": "1899183270326769471",
                      "number": 6,
                      "place": "3",
                      "startTime": "1644402000",
                      "endTime": "1644404700",
                      "hours": {
                        "startHour": "13",
                        "startMinute": "20",
                        "endHour": "14",
                        "endMinute": "05"
                      },
                      "isCanceled": false,
                      "theme": "Детское восприятие родной природы в рассказе И.А. Бунина \"В деревне\".",
                      "meetingUrl": null,
                      "subject": {
                        "id": "1718365935742488",
                        "name": "Литература",
                        "knowledgeArea": "Philology"
                      },
                      "importantWorks": [],
                      "homework": {
                        "text": "прочитать рассказ И.А. Бунина \"В деревне\".",
                        "isCompleted": false,
                        "workIsAttachRequired": false,
                        "attachments": [],
                        "userAttachments": [],
                        "lessonId": null
                      },
                      "hasAttachment": false,
                      "workMarks": [],
                      "isEmpty": false,
                      "comment": null
                    }
                  ]
                }
              ],
              "chatStub": {
                "jid": "chat_students_1577447927313437268@muclight.xmpp.dnevnik.ru"
              }
            }
            """.trimIndent()

        val lessons = objectMapper.readValue<GetHomeWorkResponse>(given).days[0].lessons

        assertEquals(0, lessons.size)
        // TODO: implement real asserts
    }
}
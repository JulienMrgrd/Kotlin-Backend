package fr.ippon.codingdojo.todolist.entity

import java.time.LocalDateTime

interface Entity {
    val id: String
    val createdAt: LocalDateTime
    var title: String
    var message: String
    var done: Boolean
}

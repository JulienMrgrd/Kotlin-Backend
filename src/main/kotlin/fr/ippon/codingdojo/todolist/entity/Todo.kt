package fr.ippon.codingdojo.todolist.entity

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class Todo (
        val id: String = UUID.randomUUID().toString(),
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var title: String = "",
        var message: String = "",
        var done: Boolean = false
): Serializable
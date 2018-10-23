package fr.ippon.codingdojo.todolist.entity

import fr.ippon.codingdojo.todolist.uuid
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class Todo (
        val id: UUID = UUID.randomUUID(),
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var title: String = "",
        var message: String = "",
        var done: Boolean = false
): Serializable {
    constructor (id: String, createdAt: LocalDateTime, title: String, message: String, done: Boolean) :
            this(uuid(id), createdAt, title, message, done)
}
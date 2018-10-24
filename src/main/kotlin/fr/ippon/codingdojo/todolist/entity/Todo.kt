package fr.ippon.codingdojo.todolist.entity

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

data class Todo (
    override val id: String = UUID.randomUUID().toString(),
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override var title: String = "",
    override var message: String = "",
    override var done: Boolean = false
): Entity, Serializable
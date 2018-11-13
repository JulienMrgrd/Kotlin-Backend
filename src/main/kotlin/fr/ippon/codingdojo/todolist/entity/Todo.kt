package fr.ippon.codingdojo.todolist.entity

import java.time.LocalDateTime

data class Todo(override val id: String = "",
                override val createdAt: LocalDateTime = LocalDateTime.now(),
                override var title: String = "",
                override var message: String = "",
                override var done: Boolean = false) : Entity {

}
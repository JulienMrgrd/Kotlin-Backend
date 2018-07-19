package fr.ippon.codingdojo.todolist.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Todo(
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = 0,
        val createdAt: LocalDateTime = LocalDateTime.now(),
        var title: String = "",
        var message: String = "",
        var done: Boolean = false
)
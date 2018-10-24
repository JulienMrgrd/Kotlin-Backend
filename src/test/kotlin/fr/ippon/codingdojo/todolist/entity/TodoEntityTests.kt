package fr.ippon.codingdojo.todolist.entity

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class TodoEntityTests {

    @Test
    fun create_new() {
        val todo = Todo(title = "titre test", message = "message test")

        assert(todo.title == "titre test")
        assert(todo.message == "message test")
        assert(!todo.done)
    }

    @Test
    fun for_mapping() {
        val d = LocalDateTime.of(2012, 8, 17, 18, 47)
        val todo = Todo(id = "1", createdAt = d, title = "titre test", message = "message test", done = true)

        assert(todo.id == "1")
        assert(todo.createdAt == d)
        assert(todo.title == "titre test")
        assert(todo.message == "message test")
        assert(todo.done)
    }
}

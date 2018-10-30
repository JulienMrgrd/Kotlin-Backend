package fr.ippon.codingdojo.todolist.entity

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class TodoEntityTests {

    @Test
    fun create_new() {
        val todo: Entity = Todo(title = "titre test", message = "message test")

        assertEquals("titre test", todo.title)
        assertEquals("message test", todo.message)
        assertFalse(todo.done)
    }

    @Test
    fun for_mapping() {
        val d = LocalDateTime.of(2012, 8, 17, 18, 47)
        val todo: Entity = Todo(id = "1", createdAt = d, title = "titre test", message = "message test", done = true)

        assertEquals("1", todo.id)
        assertEquals(d, todo.createdAt)
        assertEquals("titre test", todo.title)
        assertEquals("message test", todo.message)
        assertTrue(todo.done)
    }
}

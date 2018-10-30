package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class TodoRepositoryTests {

    private val todoRepository = TodoRepository()

    @Before
    fun before() {
        TodoListManager.deleteAll()
        TodoListManager.add(Todo("991", LocalDateTime.of(2012, 8, 17, 18, 47), "lala", "lala", false))
        TodoListManager.add(Todo("992", LocalDateTime.of(2014, 9, 17, 18, 47), "bibi", "bubu", true))
        TodoListManager.add(Todo("993", LocalDateTime.of(2016, 5, 17, 18, 47), "titi", "toto", false))
        TodoListManager.add(Todo("994", LocalDateTime.of(2018, 3, 17, 18, 47), "lala", "lala", true))
        TodoListManager.add(Todo("995", LocalDateTime.of(2017, 6, 17, 18, 47), "lala", "lala", false))
        TodoListManager.add(Todo("996", LocalDateTime.of(2015, 9, 17, 18, 47), "lala", "lala", false))
    }

    @Test
    fun add_todo() {
        val t = todoRepository.save(Todo(createdAt =  LocalDateTime.now(), title = "Test", message = "message test", done = false))
        assertNotNull(t.id)
    }

    @Test
    fun update_todo() {
        todoRepository.update(Todo("994", LocalDateTime.of(2018, 3, 17, 18, 47), "lala", "lulu", true))
        assertEquals("lulu", TodoListManager.get("994")?.message)
    }

    @Test
    fun delete_many_todos() {
        todoRepository.deleteByIds(listOf("995", "996"))
        assertEquals(4, TodoListManager.count())
    }

}
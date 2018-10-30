package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import org.assertj.core.api.Assertions
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class TodoManagerTests {

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
    fun count() {
        assert(TodoListManager.count() is Int)
        assertEquals(6, TodoListManager.count())
    }

    @Test
    fun find_ok() {
        val t = TodoListManager.get("993")
        assertNotNull(t)
        assertEquals("993", t!!.id)
    }

    @Test
    fun find_ko() {
        val t = TodoListManager.get("3")
        assertNull(t)
    }

    @Test
    fun find_all() {
        val todolist = TodoListManager.getAll()
        Assertions.assertThat(todolist?.map { it.id }).containsAll(listOf("991", "992", "993"))
        assertEquals(6, TodoListManager.count())
    }

    @Test
    fun update() {
        val new = Todo("995", LocalDateTime.of(2017, 6, 17, 18, 47), "Nouveau titre", "nouveau message", true)
        TodoListManager.update(new)

        val t = TodoListManager.get("995")
        assertNotNull(t)
        assertEquals("Nouveau titre", t?.title)
        assertEquals("nouveau message", t?.message)
        assertEquals(true, t?.done)
    }

    @Test
    fun delete() {
        TodoListManager.delete("994")
        assertEquals(5, TodoListManager.count())
    }

    @Test
    fun delete_all() {
        TodoListManager.deleteAll()
        assertEquals(0, TodoListManager.count())
    }
}

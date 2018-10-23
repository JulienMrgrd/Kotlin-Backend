package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.uuid
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class TodoRepositoryTests {

    @Autowired
    val todoRepository = TodoRepository()

    @Before
    fun before() {
        TodoListManager.deleteAll()
        TodoListManager.add(Todo(uuid("991"), LocalDateTime.of(2012, 8, 17, 18, 47), "lala", "lala", false))
        TodoListManager.add(Todo(uuid("992"), LocalDateTime.of(2014, 9, 17, 18, 47), "bibi", "bubu", true))
        TodoListManager.add(Todo(uuid("993"), LocalDateTime.of(2016, 5, 17, 18, 47), "titi", "toto", false))
        TodoListManager.add(Todo(uuid("994"), LocalDateTime.of(2018, 3, 17, 18, 47), "lala", "lala", true))
        TodoListManager.add(Todo(uuid("995"), LocalDateTime.of(2017, 6, 17, 18, 47), "lala", "lala", false))
        TodoListManager.add(Todo(uuid("996"), LocalDateTime.of(2015, 9, 17, 18, 47), "lala", "lala", false))
    }

    @Test
    fun add_todo() {
        val t = todoRepository.save(Todo(createdAt =  LocalDateTime.now(), title = "Test", message = "message test", done = false))
        assert(t.id != null)
    }

    @Test
    fun update_todo() {
        todoRepository.update(Todo(uuid("994"), LocalDateTime.of(2018, 3, 17, 18, 47), "lala", "lulu", true))
        assert(TodoListManager.get(uuid("994"))?.message.equals("lulu"))
    }

    @Test
    fun delete_many_todos() {
        todoRepository.deleteByIds(listOf(uuid("995"), uuid("996")))
        assert(TodoListManager.count() == 4)
    }

}
package fr.ippon.codingdojo.todolist.repository

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class TodoRepositoryTests {

    @Autowired
    lateinit var todoRepository: TodoRepository

    @Test
    fun delete_many_todos() {
        todoRepository.deleteByIds(listOf(995, 996))
    }
}
package fr.ippon.codingdojo.todolist.api

import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoListManager
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiIntegrationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    init {
        TodoListManager.add(Todo("991", LocalDateTime.of(2012, 8, 17, 18, 47), "lala", "lala", false))
        TodoListManager.add(Todo("992", LocalDateTime.of(2014, 9, 17, 18, 47), "bibi", "bubu", true))
        TodoListManager.add(Todo("993", LocalDateTime.of(2016, 5, 17, 18, 47), "titi", "toto", false))
        TodoListManager.add(Todo("994", LocalDateTime.of(2018, 3, 17, 18, 47), "lala", "lala", true))
        TodoListManager.add(Todo("995", LocalDateTime.of(2017, 6, 17, 18, 47), "lala", "lala", false))
        TodoListManager.add(Todo("996", LocalDateTime.of(2015, 9, 17, 18, 47), "lala", "lala", false))
    }

    @Test
    fun say_welcome() {
        Assertions.assertThat(restTemplate.getForObject("/", String::class.java)).contains("Welcome to Todolist Application")
    }

    @Test
    fun get_todolist() {
        val todolist = restTemplate.exchange("/todolist", HttpMethod.GET, null, object : ParameterizedTypeReference<List<Todo>>() {}).body

        Assertions.assertThat(todolist?.map { it.id }).containsAll(listOf("991", "992"))
    }

    data class Todolist (val todolist: MutableList<Todo> = ArrayList())

    @Test
    fun find_one_todo() {
        val todo = restTemplate.getForObject("/todolist/991", Todo::class.java)

        Assertions.assertThat(todo.id).isEqualTo("991")
        Assertions.assertThat(todo.done).isEqualTo(false)
    }

    @Test
    fun create_one_todo() {
        val todo = Todo(title = "Le test", message = "Tester l'application")
        val response = restTemplate.postForEntity("/todolist", todo, Todo::class.java)

        Assertions.assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun delete_one_todo() {
        restTemplate.delete("/todolist/993")

        val todo = restTemplate.getForObject("/todolist/993", Todo::class.java)
        Assertions.assertThat(todo).isNull()
    }

    @Test
    fun delete_many_todo() {
        restTemplate.delete("/todolist?ids=994,995,996")

        val todo = restTemplate.getForObject("/todolist/995", Todo::class.java)
        Assertions.assertThat(todo).isNull()
    }

}

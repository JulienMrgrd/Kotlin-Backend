package fr.ippon.codingdojo.todolist.api

import fr.ippon.codingdojo.todolist.entity.Todo
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiIntegrationTests {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun say_welcome() {
        Assertions.assertThat(restTemplate.getForObject("/", String::class.java)).contains("Welcome to Todolist Application")
    }

    @Test
    fun get_todolist() {
        val todolist = restTemplate.getForObject("/todolist", List::class.java) as List<Todo>

        Assertions.assertThat(todolist.size.equals(6))
    }

    @Test
    fun find_one_todo() {
        val todo = restTemplate.getForObject("/todolist/991", Todo::class.java)

        Assertions.assertThat(todo.id.equals(991))
        Assertions.assertThat(!todo.done)
    }

    @Test
    fun create_one_todo() {
        val todo = Todo(title = "Le test", message = "Tester l'application")
        val response = restTemplate.postForEntity("/todolist", todo, Todo::class.java)

        Assertions.assertThat(response.statusCode.equals(HttpStatus.ACCEPTED))
    }

    @Test
    fun delete_one_todo() {
        restTemplate.delete("/todolist/993")
    }

    @Test
    fun delete_many_todo() {
        try {
            restTemplate.delete("/todolist?ids=994,995,996")
        } catch (e: Exception) {
            Assert.fail(e.message)
        }
    }
}

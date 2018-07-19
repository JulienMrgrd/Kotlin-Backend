package fr.ippon.codingdojo.todolist.web.rest

import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoRepository
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.util.*


@RunWith(SpringRunner::class)
@WebMvcTest(TodoController::class)
class TodoControllerTests {


    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var todoRepository: TodoRepository

    @Test
    fun get_todolist() {

        val todo1 = Todo(1, LocalDateTime.now(), "Titre 1", "Message 1", false)
        val todo2 = Todo(2, LocalDateTime.now(), "Titre 2", "Message 2", false)
        val todolist = listOf(todo1, todo2)

        given(todoRepository.findAll()).willReturn(todolist)

        mvc.perform(get("/todolist").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", `is`(1)))
                .andExpect(jsonPath("$[1].id", `is`(2)))

    }

    @Test
    fun find_one_todo() {
        val todo1 = Todo(1, LocalDateTime.now(), "Titre 1", "Message 1", false)

        given(todoRepository.findById(1)).willReturn(Optional.of(todo1))

        mvc.perform(get("/todolist/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", `is`(1)))
                .andExpect(jsonPath("$.done", `is`(false)))
    }

    @Test
    fun create_one_todo() {
        val createdTodo = Todo(1, LocalDateTime.now(), "le titre", "le message", false)

        given(todoRepository.save(any(Todo::class.java))).willReturn(createdTodo)

        mvc.perform(
                    post("/todolist")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"title\":\"le titre\", \"message\":\"le message\"}")
                )
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.message", `is`("le message")))
    }

    @Test
    fun delete_one_todo() {
        willDoNothing().given(todoRepository).deleteById(7)

        mvc.perform(
                    delete("/todolist/7")
                )
                .andExpect(status().isOk)
    }

    @Test
    fun delete_many_todo() {
        willDoNothing().given(todoRepository).deleteByIds(listOf(7,8,9))

        mvc.perform(
                    delete("/todolist?ids=7,8,9")
                )
                .andExpect(status().isOk)
    }
}
package fr.ippon.codingdojo.todolist.web.rest

import com.nhaarman.mockito_kotlin.any
import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoRepository
import fr.ippon.codingdojo.todolist.uuid
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
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

@RunWith(SpringRunner::class)
@WebMvcTest(TodoController::class)
class TodoControllerTests {

    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var todoRepository: TodoRepository

    @Test
    fun get_todolist() {

        val todo1 = Todo(uuid("1"), LocalDateTime.now(), "Titre 1", "Message 1", false)
        val todo2 = Todo(uuid("2"), LocalDateTime.now(), "Titre 2", "Message 2", false)
        val todolist = listOf(todo1, todo2)

        given(todoRepository.findAll()).willReturn(todolist)

        mvc.perform(get("/todolist").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", `is`(uuid("1").toString())))
                .andExpect(jsonPath("$[1].id", `is`(uuid("2").toString())))

    }

    @Test
    fun find_one_todo() {
        val todo1 = Todo(uuid("1"), LocalDateTime.now(), "Titre 1", "Message 1", false)

        given(todoRepository.findById(uuid("1"))).willReturn(todo1)

        mvc.perform(get("/todolist/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", `is`(uuid("1").toString())))
                .andExpect(jsonPath("$.done", `is`(false)))
    }

    @Test
    fun create_one_todo() {
        val createdTodo = Todo(uuid("1"), LocalDateTime.now(), "le titre", "le message", false)

        given(todoRepository.save(any())).willReturn(createdTodo)

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
        willDoNothing().given(todoRepository).deleteById(uuid("7"))

        mvc.perform(
                    delete("/todolist/7")
                )
                .andExpect(status().isOk)
    }

    @Test
    fun delete_many_todo() {
        willDoNothing().given(todoRepository).deleteByIds(listOf(uuid("7"), uuid("8"), uuid("9")))

        mvc.perform(
                    delete("/todolist?ids=7,8,9")
                )
                .andExpect(status().isOk)
    }
}
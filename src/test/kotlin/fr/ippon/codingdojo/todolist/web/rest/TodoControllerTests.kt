package fr.ippon.codingdojo.todolist.web.rest

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.verify
import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoRepository
import org.assertj.core.api.Assertions
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.willDoNothing
import org.mockito.Captor
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

    @Captor
    lateinit var todoArgument: ArgumentCaptor<Todo>

    @Test
    fun get_todolist() {

        val todo1 = Todo("1", LocalDateTime.now(), "Titre 1", "Message 1", false)
        val todo2 = Todo("2", LocalDateTime.now(), "Titre 2", "Message 2", false)
        val todolist = listOf(todo1, todo2)

        given(todoRepository.findAll()).willReturn(todolist)

        mvc.perform(get("/todolist").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", `is`("1".toString())))
                .andExpect(jsonPath("$[1].id", `is`("2".toString())))

    }

    @Test
    fun find_one_todo() {
        val todo1 = Todo("1", LocalDateTime.now(), "Titre 1", "Message 1", false)

        given(todoRepository.findById("1")).willReturn(todo1)

        mvc.perform(get("/todolist/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", `is`("1".toString())))
                .andExpect(jsonPath("$.done", `is`(false)))
    }

    @Test
    fun find_one_not_found() {
        given(todoRepository.findById("111")).willReturn(null)

        mvc.perform(get("/todolist/111").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound)
    }

    @Test
    fun create_one_todo() {
        val createdTodo = Todo("1", LocalDateTime.now(), "le titre", "le message", false)

        given(todoRepository.save(any())).willReturn(createdTodo)

        mvc.perform(
                post("/todolist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"le titre\", \"message\":\"le message\"}")
        )
        .andExpect(status().isOk)
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.message", `is`("le message")))

        verify(todoRepository).save(capture(todoArgument))

        Assertions.assertThat(todoArgument.value.title).isEqualTo("le titre")
        Assertions.assertThat(todoArgument.value.message).isEqualTo("le message")
    }

    @Test
    fun patch_todo() {
        val updatedTodo = Todo("9", LocalDateTime.now(), "meilleur titre", "nouveau message", false)

        given(todoRepository.update(any())).willReturn(updatedTodo)

        mvc.perform(
                patch("/todolist")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"9\", \"createdAt\": \"2014-11-19T18:47:00\", \"title\":\"meilleur titre\", \"message\":\"nouveau message\", \"done\": false}")
        )
        .andExpect(status().isOk)
        .andExpect(jsonPath("$.id", `is`("9")))
        .andExpect(jsonPath("$.message", `is`("nouveau message")))

        verify(todoRepository).update(capture(todoArgument))

        Assertions.assertThat(todoArgument.value.id).isEqualTo("9")
        Assertions.assertThat(todoArgument.value.title).isEqualTo("meilleur titre")
        Assertions.assertThat(todoArgument.value.message).isEqualTo("nouveau message")

    }

    @Test
    fun patch_ko() {
        given(todoRepository.update(any())).willReturn(null)

        mvc.perform(
                patch("/todolist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"99\", \"createdAt\": \"2014-11-19T18:47:00\", \"title\":\"meilleur titre\", \"message\":\"nouveau message\", \"done\": false}")
        )
        .andExpect(status().isNotFound)
    }

    @Test
    fun delete_one_todo() {
        willDoNothing().given(todoRepository).deleteById("7")

        mvc.perform(delete("/todolist/7")).andExpect(status().isOk)
    }

    @Test
    fun delete_many_todo() {
        willDoNothing().given(todoRepository).deleteByIds(listOf("7", "8", "9"))

        mvc.perform(delete("/todolist?ids=7,8,9")).andExpect(status().isOk)
    }
}
package fr.ippon.codingdojo.todolist.web.rest

import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoRepository
import fr.ippon.codingdojo.todolist.uuid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todolist")
class TodoController (@Autowired val todoRepository: TodoRepository) {

    @PostMapping
    fun save(@RequestBody todo: Todo) =
        todoRepository.save(todo)

    @PatchMapping
    fun update(@RequestBody todo: Todo) =
        todoRepository.save(todo)

    // hint : command let
    @GetMapping("/{id}")
    fun get(@PathVariable id: String) = todoRepository.findById(uuid(id))?.let { ResponseEntity.ok(it) }

    @GetMapping
    fun todolist() =
        ResponseEntity.ok(todoRepository.findAll())

    @DeleteMapping("/{id}")
    fun deleteOne (@PathVariable id: String) =
            todoRepository.deleteById(uuid(id))

    @DeleteMapping
    fun deleteMany (@RequestParam ids: List<String>) =
            todoRepository.deleteByIds(ids.map { uuid(it) })
}

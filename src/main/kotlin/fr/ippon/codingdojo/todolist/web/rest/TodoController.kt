package fr.ippon.codingdojo.todolist.web.rest

import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoRepository
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

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) =
            todoRepository.findById(id).map { t -> ResponseEntity.ok(t) }.orElse(ResponseEntity.notFound().build())

    @GetMapping
    fun todolist() =
        ResponseEntity.ok(todoRepository.findAll())

    @DeleteMapping("/{id}")
    fun deleteOne (@PathVariable id: Long) =
            todoRepository.deleteById(id)

    @DeleteMapping
    fun deleteMany (@RequestParam ids: List<Long>) =
            todoRepository.deleteByIds(ids)
}

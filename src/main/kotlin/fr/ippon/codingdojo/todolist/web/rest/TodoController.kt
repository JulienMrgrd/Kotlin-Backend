package fr.ippon.codingdojo.todolist.web.rest

import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todolist")
class TodoController (@Autowired val todoRepository: TodoRepository) : Controller<Todo> {

    @PostMapping
    override fun save(@RequestBody todo: Todo) = todoRepository.save(todo)

    @PatchMapping
    override fun update(@RequestBody todo: Todo) = todoRepository.update(todo)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping("/{id}")
    override fun getOne(@PathVariable id: String) = todoRepository.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping
    override fun getAll() = ResponseEntity.ok(todoRepository.findAll())

    @DeleteMapping("/{id}")
    override fun deleteOne (@PathVariable id: String) = todoRepository.deleteById(id)

    @DeleteMapping
    override fun deleteMany (@RequestParam ids: List<String>) = todoRepository.deleteByIds(ids)
}

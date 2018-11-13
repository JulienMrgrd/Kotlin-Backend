package fr.ippon.codingdojo.todolist.web.rest

import fr.ippon.codingdojo.todolist.entity.Todo
import fr.ippon.codingdojo.todolist.repository.TodoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todolist")
class TodoController(val repo: TodoRepository) : Controller<Todo> {
    
    @PostMapping
    override fun save(@RequestBody entity: Todo): Todo = repo.save(entity)

    @PatchMapping
    override fun update(@RequestBody entity: Todo) = repo.update(entity)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping("/{id}")
    override fun getOne(@PathVariable id: String) = repo.findById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @GetMapping
    override fun getAll(): ResponseEntity<List<Todo>> = ResponseEntity.ok(repo.findAll())

    @DeleteMapping("/{id}")
    override fun deleteOne(@PathVariable id: String) = repo.deleteById(id)

    @DeleteMapping
    override fun deleteMany(@RequestParam ids: List<String>) = repo.deleteByIds(ids)
}
package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TodoRepository: fr.ippon.codingdojo.todolist.repository.Repository<Todo> {

    override fun findById(id: UUID): Todo? = TodoListManager.get(id)

    override fun deleteByIds(ids: List<UUID>) {
        for (id in ids) {
            TodoListManager.delete(id)
        }
    }

    override fun save(todo: Todo): Todo {
        TodoListManager.add(todo)
        return todo
    }

    override fun update(todo: Todo): Todo {
        TodoListManager.update(todo)
        return todo
    }

    override fun findAll() = TodoListManager.getAll()

    override fun deleteById(id: UUID) {
        TodoListManager.delete(id)
    }

}
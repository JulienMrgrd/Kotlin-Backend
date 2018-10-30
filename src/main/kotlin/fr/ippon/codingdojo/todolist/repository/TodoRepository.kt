package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import org.springframework.stereotype.Component

@Component
class TodoRepository: Repository<Todo> {

    override fun findById(id: String): Todo? = TodoListManager.get(id)

    override fun deleteByIds(ids: List<String>) {
        for (id in ids) {
            TodoListManager.delete(id)
        }
    }

    override fun save(todo: Todo) = TodoListManager.add(todo).let { todo }

    override fun update(todo: Todo) = TodoListManager.get(todo.id)?.let {
        TodoListManager.update(todo)
        todo
    }

    override fun findAll(): List<Todo> = TodoListManager.getAll()

    override fun deleteById(id: String) {
        TodoListManager.delete(id)
    }

}
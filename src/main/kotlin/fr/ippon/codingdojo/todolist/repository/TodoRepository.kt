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

    override fun save(todo: Todo): Todo {
        TodoListManager.add(todo)
        return todo
    }

    override fun update(todo: Todo): Todo? {
        val new = TodoListManager.get(todo.id)
        new?.let {
            it.title = todo.title
            it.message = todo.message
            it.done = todo.done
            TodoListManager.update(new)
        }
        return new
    }

    override fun findAll() = TodoListManager.getAll()

    override fun deleteById(id: String) {
        TodoListManager.delete(id)
    }

}
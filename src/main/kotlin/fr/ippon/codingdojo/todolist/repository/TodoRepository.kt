package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import org.springframework.stereotype.Repository

@Repository
class TodoRepository: fr.ippon.codingdojo.todolist.repository.Repository<Todo> {

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

    override fun update(todo: Todo): Todo {
        TodoListManager.update(todo)
        return todo
    }

    override fun findAll() = TodoListManager.getAll()

    override fun deleteById(id: String) {
        TodoListManager.delete(id)
    }

}
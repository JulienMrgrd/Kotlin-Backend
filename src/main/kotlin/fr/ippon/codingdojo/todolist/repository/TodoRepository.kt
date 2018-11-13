package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo

@org.springframework.stereotype.Repository
class TodoRepository : Repository<Todo> {

    override fun deleteByIds(ids: List<String>) = ids.forEach { TodoListManager.delete(it) }

    override fun save(entity: Todo): Todo = TodoListManager.add(entity)

    override fun update(entity: Todo): Todo? {
        TodoListManager.update(entity)
    }

    override fun findById(id: String): Todo? = TodoListManager.get(id)

    override fun findAll(): List<Todo> = TodoListManager.getAll()

    override fun deleteById(id: String) = TodoListManager.delete(id)
}
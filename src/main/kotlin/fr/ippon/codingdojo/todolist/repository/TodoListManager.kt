package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import java.util.*

object TodoListManager : Manager<Todo> {
    override val list: MutableMap<String, Todo> = HashMap()

    override fun add(t: Todo): Todo = list.put(t.id, t) ?: t

    override fun update(t: Todo) {
        list.replace(t.id, t)
    }

    override fun get(id: String): Todo? = list.get(id)

    override fun getAll(): List<Todo> = ArrayList(list.values)

    override fun delete(id: String) {
        list.remove(id)
    }

    override fun deleteAll() {
        list.clear()
    }

    override fun count(): Int = list.size

    override fun backup() {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun load() {
        //To change body of created functions use File | Settings | File Templates.
    }

}
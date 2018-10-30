package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import java.io.*
import java.util.*

object TodoListManager : Manager<Todo> {

    private const val fileName = "todo.list"

    override val list: MutableMap<String, Todo> = HashMap()

    override fun add(todo: Todo): Todo {
        list[todo.id] = todo
        return todo
    }

    override fun update(t: Todo) {
        list.replace(t.id, t)
    }

    override fun get(id: String): Todo? = list[id]

    override fun getAll() = ArrayList(list.values)


    override fun delete(id: String) {
        list.remove(id)
    }

    override fun deleteAll() {
        list.clear()
    }

    override fun count() = list.size

    override fun backup() {
        ObjectOutputStream(FileOutputStream(fileName)).use { it.writeObject(list) }
    }

    override fun load() {
        try {
            ObjectInputStream(FileInputStream(fileName)).use {
                val restedList = it.readObject()

                when (restedList) {
                    //We can't use <String, String> because of type erasure
                    is Map<*, *> -> restedList.forEach { (k, v) -> list[k as String] = v as Todo }
                    else -> println("Deserialization failed")
                }
            }
        } catch (e: FileNotFoundException) {
            println("File $fileName not found")
        }
    }

}
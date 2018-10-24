package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Entity

interface Manager<E : Entity> {

    val list: MutableMap<String, E>

    fun add(t: E): E
    fun update(t: E)
    fun get(id: String): E?
    fun getAll(): List<E>
    fun delete(id: String)
    fun deleteAll()
    fun count(): Int
    fun backup()
    fun load()
}

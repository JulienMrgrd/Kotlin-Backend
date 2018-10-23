package fr.ippon.codingdojo.todolist.repository

import java.util.*

interface Manager<T> {

    val list: MutableMap<UUID, T>

    fun add(t: T): T
    fun update(t: T)
    fun get(id: UUID): T?
    fun getAll(): List<T>
    fun delete(id: UUID)
    fun deleteAll()
    fun count(): Int
    fun backup()
    fun load()
}

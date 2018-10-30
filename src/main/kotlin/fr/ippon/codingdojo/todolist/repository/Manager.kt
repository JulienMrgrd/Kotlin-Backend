package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Entity

interface Manager<E : Entity> {

    val list: MutableMap<String, E>

    /** Add new todo */
    fun add(t: E): E

    /** Update existing todo */
    fun update(t: E)

    /** Get one todo by id */
    fun get(id: String): E?

    /** Get all todo */
    fun getAll(): List<E>

    /** Delete one todo by id */
    fun delete(id: String)

    /** Delete all todo */
    fun deleteAll()

    /** Number of todo */
    fun count(): Int

    /** serialize todolist in file */
    fun backup()

    /** deserialize todolist from file */
    fun load()
}

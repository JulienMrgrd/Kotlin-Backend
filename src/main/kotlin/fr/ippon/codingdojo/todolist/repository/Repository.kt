package fr.ippon.codingdojo.todolist.repository

import java.util.*

interface Repository<E> {

    fun deleteByIds(ids: List<UUID>)

    fun save(e: E): E

    fun update(e: E): E

    fun findById(id: UUID): E?

    fun findAll(): List<E>

    fun deleteById(id: UUID)
}
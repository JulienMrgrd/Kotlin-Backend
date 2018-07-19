package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Entity

interface Repository<E : Entity> {

    fun deleteByIds(ids: List<String>)

    fun save(entity: E): E

    fun update(entity: E): E?

    fun findById(id: String): E?

    fun findAll(): List<E>

    fun deleteById(id: String)
}
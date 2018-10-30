package fr.ippon.codingdojo.todolist.web.rest

import fr.ippon.codingdojo.todolist.entity.Entity
import org.springframework.http.ResponseEntity

interface Controller<E : Entity> {

    fun save(entity: E): E

    fun update(entity: E): ResponseEntity<*>

    fun getOne(id: String): ResponseEntity<*>

    fun getAll(): ResponseEntity<List<E>>

    fun deleteOne (id: String)

    fun deleteMany (ids: List<String>)
}

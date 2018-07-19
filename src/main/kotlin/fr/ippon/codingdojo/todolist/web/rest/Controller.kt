package fr.ippon.codingdojo.todolist.web.rest

import fr.ippon.codingdojo.todolist.entity.Entity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface Controller<E : Entity> {

    fun save(@RequestBody entity: E): E

    fun update(@RequestBody entity: E): ResponseEntity<*>

    fun getOne(@PathVariable id: String): ResponseEntity<*>

    fun getAll(): ResponseEntity<List<E>>

    fun deleteOne (@PathVariable id: String)

    fun deleteMany (@RequestParam ids: List<String>)
}

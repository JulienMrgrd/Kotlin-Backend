package fr.ippon.codingdojo.todolist.web.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface Controller<Entity> {

    fun save(@RequestBody entity: Entity): Entity

    fun update(@RequestBody entity: Entity): ResponseEntity<*>

    fun getOne(@PathVariable id: String): ResponseEntity<*>

    fun getAll(): ResponseEntity<List<Entity>>

    fun deleteOne (@PathVariable id: String)

    fun deleteMany (@RequestParam ids: List<String>)
}

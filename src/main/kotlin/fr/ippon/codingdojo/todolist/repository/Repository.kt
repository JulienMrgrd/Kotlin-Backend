package fr.ippon.codingdojo.todolist.repository

interface Repository<Entity> {

    fun deleteByIds(ids: List<String>)

    fun save(e: Entity): Entity

    fun update(e: Entity): Entity

    fun findById(id: String): Entity?

    fun findAll(): List<Entity>

    fun deleteById(id: String)
}
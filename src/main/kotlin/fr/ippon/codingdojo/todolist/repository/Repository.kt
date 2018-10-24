package fr.ippon.codingdojo.todolist.repository

interface Repository<Entity> {

    fun deleteByIds(ids: List<String>)

    fun save(entity: Entity): Entity

    fun update(entity: Entity): Entity?

    fun findById(id: String): Entity?

    fun findAll(): List<Entity>

    fun deleteById(id: String)
}
package fr.ippon.codingdojo.todolist.repository

interface Manager<Entity> {

    val list: MutableMap<String, Entity>

    fun add(t: Entity): Entity
    fun update(t: Entity)
    fun get(id: String): Entity?
    fun getAll(): List<Entity>
    fun delete(id: String)
    fun deleteAll()
    fun count(): Int
    fun backup()
    fun load()
}

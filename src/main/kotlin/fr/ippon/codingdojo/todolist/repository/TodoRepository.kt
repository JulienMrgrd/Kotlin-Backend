package fr.ippon.codingdojo.todolist.repository

import fr.ippon.codingdojo.todolist.entity.Todo
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface TodoRepository: CrudRepository<Todo, Long> {

    @Transactional
    @Modifying
    @Query("delete from Todo where id in :ids")
    fun deleteByIds(ids: List<Long>)
}
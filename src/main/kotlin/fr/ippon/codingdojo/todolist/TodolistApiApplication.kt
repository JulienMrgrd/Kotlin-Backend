package fr.ippon.codingdojo.todolist

import fr.ippon.codingdojo.todolist.repository.TodoListManager
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@SpringBootApplication
@EnableSwagger2
class TodolistApiApplication {

    @PostConstruct
    fun init() {
        TodoListManager.load()
    }

    @PreDestroy
    fun onDestroy() {
        TodoListManager.backup()
    }
}

    fun main(args: Array<String>) {
        SpringApplication.run(TodolistApiApplication::class.java, *args)
    }


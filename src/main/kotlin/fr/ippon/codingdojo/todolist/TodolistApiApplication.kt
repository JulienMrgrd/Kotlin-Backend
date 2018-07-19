package fr.ippon.codingdojo.todolist

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class TodolistApiApplication

    fun main(args: Array<String>) {
        SpringApplication.run(TodolistApiApplication::class.java, *args)
    }


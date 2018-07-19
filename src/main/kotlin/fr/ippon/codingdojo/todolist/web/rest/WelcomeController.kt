package fr.ippon.codingdojo.todolist.web.rest

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @GetMapping("/")
    fun welcome(): ResponseEntity<String> {
        return ResponseEntity.ok("Welcome to Todolist Application")
    }
}
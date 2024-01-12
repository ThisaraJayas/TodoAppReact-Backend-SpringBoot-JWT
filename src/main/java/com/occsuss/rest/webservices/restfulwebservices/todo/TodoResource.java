package com.occsuss.rest.webservices.restfulwebservices.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoResource {

    private TodoService todoService;

    public TodoResource(TodoService todoService){
        this.todoService=todoService;
    }

    @GetMapping("/users/{username}/todos")
    public List<Todo> retriveTodos(@PathVariable String username){
        return todoService.findByUsername(username);
    }
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retriveTodo(@PathVariable String username,@PathVariable int id){
        return todoService.findById(id);
    }
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id){
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
        todoService.updateTodo(todo);
        return todo;
    }
    @PostMapping  ("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        Todo createdTodo = todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(),todo.isDone());
        return createdTodo;
    }
}

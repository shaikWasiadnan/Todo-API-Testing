package com.example.TodoApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TodoController {
    private static List<Todo> TodoList;
    public TodoController(){
        TodoList=new ArrayList<>();
        TodoList.add(new Todo(1,true,"adnan",51));
        TodoList.add(new Todo(2,false,"sameer",50));
    }
    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos(){
        return ResponseEntity.ok(TodoList);
    }
    @PostMapping("/todos")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo newTodo){
        TodoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }
    @GetMapping("/todos/{todoId}")
    public ResponseEntity<?> getById(@PathVariable Long todoId){
        for(Todo todo:TodoList){
            if(todo.getId()==todoId){
                return ResponseEntity.ok(todo);
            }
        }
        Map<String,String> response=new HashMap<>();
        response.put("message","UserId is not present");
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<?> deleteById(@PathVariable Long todoId){
        for (Todo todo:TodoList){
            if(todo.getId()==todoId){
                TodoList.remove(todo);
                return ResponseEntity.ok(TodoList);
            }

        }
        Map<String,String> response2=new HashMap<>();
        response2.put("message","Todo with "+todoId + " is not found");
        return new ResponseEntity<>(response2,HttpStatus.NOT_FOUND);
    }

}

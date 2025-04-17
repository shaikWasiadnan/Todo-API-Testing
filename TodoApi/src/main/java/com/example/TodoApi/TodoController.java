package com.example.TodoApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService1;
    private TodoService todoService2;
    private static List<Todo> TodoList;
    public TodoController(@Qualifier("First") TodoService todoService1,
                          @Qualifier("Second") TodoService todoService2){
        this.todoService1=todoService1;
        this.todoService2=todoService2;
        TodoList=new ArrayList<>();
        TodoList.add(new Todo(1,true,"adnan",51));
        TodoList.add(new Todo(2,false,"sameer",50));
    }
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        System.out.println("This message is from: "+todoService1.print());
        System.out.println("This message is from: "+todoService2.print());
        return ResponseEntity.ok(TodoList);
    }
    @PostMapping
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> addTodo(@RequestBody Todo newTodo){
        TodoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }
    @GetMapping("/{todoId}")
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

    @DeleteMapping("/{todoId}")
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
    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateById(@PathVariable Long todoId,@RequestParam(required = false) Boolean completed,
                                        @RequestParam(required = false) String title ,
                                        @RequestParam(required = false) Integer userId){
        for (Todo todo:TodoList){
            if (todo.getId()==todoId){
                if(completed!=null){
                    todo.setCompleted(completed);
                }
                if (title!=null){
                    todo.setTitle(title);
                }
                if (userId!=null){
                    todo.setUserId(userId);
                }
                return ResponseEntity.ok(TodoList);

            }

        }
        String message3="todo with given id is not present";
        return new ResponseEntity<>(message3,HttpStatus.NOT_FOUND);

    }

}

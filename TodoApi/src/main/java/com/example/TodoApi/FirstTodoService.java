package com.example.TodoApi;

import org.springframework.stereotype.Service;

@Service("First")
public class FirstTodoService implements TodoService{
    @Override
    public String print() {
        return "This is to do from FirstTodoservice";
    }
}

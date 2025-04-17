package com.example.TodoApi;

import org.springframework.stereotype.Service;

@Service("Second")
public class SecondTodoService implements TodoService{
    @Override
    public String print() {
        return "ToDO message from Second Todo service";
    }
}

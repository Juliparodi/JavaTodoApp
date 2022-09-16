package com.juliparodi.rest.webservices.restfulwebservices.service;

import com.juliparodi.rest.webservices.restfulwebservices.model.Todo;
import com.juliparodi.rest.webservices.restfulwebservices.model.TodoDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TodoHarcodedService {

    private static List<Todo> todos = new ArrayList<>();
    static Long idCounter = 0L;

    static{
        todos.add(new Todo(idCounter++, "juliparodi", "Learn full stack development", LocalDate.now(), false));
        todos.add(new Todo(idCounter++, "juliparodi", "Invest in bitcoin", LocalDate.now(), false));
        todos.add(new Todo(idCounter++, "juliparodi", "Cook for tonight", LocalDate.now(), false));
    }

    public List<Todo> findAll() {
        return todos;
    }

    public Optional<Todo> addTodo(TodoDTO todoDTO){
        if(!findByDescription(todoDTO.getDescription()).isEmpty()){
            return Optional.empty();
        }
        LocalDate targetDate = todoDTO.getTargetDate()==null? LocalDate.now() : todoDTO.getTargetDate();
        todos.add(new Todo(idCounter++, todoDTO.getUsername(), todoDTO.getDescription(), targetDate, false));
        return findByDescription(todoDTO.getDescription());
    }

    public Optional<Todo> deleteById(long id){
        Optional<Todo> todo = findById(id).isPresent() ? findById(id) :  Optional.empty();
        if(todo.isEmpty()){
            return todo;
        } else {
            if (todos.remove(todo.get())){
                return todo;
            } else {
                return Optional.empty();
            }
        }
    }

    public Optional<Todo> findByDescription(String description){
        try {
            return todos.stream()
                .filter(todo -> todo.getDescription().equalsIgnoreCase(description))
                .findFirst();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Todo> findById(long id){
        try {
            return todos.stream()
                .filter(todo -> todo.getId() == id)
                .findFirst();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Todo> updateById(long id, Todo todo) {
        Optional<Todo> todoFound = findById(id);
        if(todoFound.isEmpty()){
            return Optional.empty();
        }
        todoFound.get().setDescription(todo.getDescription());
        todoFound.get().setTargetDate(todo.getTargetDate());
        return todoFound;
    }
}

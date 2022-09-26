package com.juliparodi.rest.webservices.restfulwebservices.service;

import com.juliparodi.rest.webservices.restfulwebservices.exception.ItemNotFoundException;
import com.juliparodi.rest.webservices.restfulwebservices.model.Todo;
import com.juliparodi.rest.webservices.restfulwebservices.model.TodoDTO;
import com.juliparodi.rest.webservices.restfulwebservices.repository.TodoJpaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoHarcodedService {

    @Autowired
    TodoJpaRepository todoJpaRepository;
    private static List<Todo> todos = new ArrayList<>();
    static Long idCounter = 0L;

    /*
    static{
        todos.add(new Todo(idCounter++, "juliparodi", "Learn full stack development", LocalDate.now(), false));
        todos.add(new Todo(idCounter++, "juliparodi", "Invest in bitcoin", LocalDate.now(), false));
        todos.add(new Todo(idCounter++, "juliparodi", "Cook for tonight", LocalDate.now(), false));
    }
     */


    public List<Todo> findAll() {
        return todoJpaRepository.findAll();
    }

    public Optional<Todo> addTodo(TodoDTO todoDTO){
        if(findByDescription(todoDTO.getDescription()).isPresent()){
            return Optional.empty();
        }
        LocalDate targetDate = todoDTO.getTargetDate()==null? LocalDate.now() : todoDTO.getTargetDate();
        Todo todo = Todo.builder()
        .username(todoDTO.getUsername())
        .description(todoDTO.getDescription())
            .targetDate(targetDate)
                .isDone(false)
                    .build();

        todoJpaRepository.save(todo);
        return findByDescription(todoDTO.getDescription());
    }

    public void deleteById(long id){
        Optional<Todo> todo = findById(id).isPresent() ? findById(id) :  Optional.empty();
        if(todo.isEmpty()){
            throw new ItemNotFoundException();
        } else {
            todoJpaRepository.deleteById(todo.get().getId());
        }
    }

    public Optional<Todo> findByDescription(String description){
        try {
            return todoJpaRepository.findAllByDescription(description)
                .stream()
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
            throw new ItemNotFoundException();
        }
        todoFound.get().setDescription(todo.getDescription());
        todoFound.get().setTargetDate(todo.getTargetDate());
        todoJpaRepository.save(todoFound.get());
        return todoFound;
    }
}

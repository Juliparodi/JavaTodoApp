package com.juliparodi.rest.webservices.restfulwebservices.controller;

import com.juliparodi.rest.webservices.restfulwebservices.exception.ItemNotFoundException;
import com.juliparodi.rest.webservices.restfulwebservices.model.Todo;
import com.juliparodi.rest.webservices.restfulwebservices.model.TodoDTO;
import com.juliparodi.rest.webservices.restfulwebservices.service.TodoHarcodedService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/juli/todoapp")
@CrossOrigin()
@RestController
public class TodoController {

    @Autowired
    TodoHarcodedService todoHarcodedService;

    @GetMapping(value = "/users/{name}/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "response OK"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Devuelve la informacion necesaria para hacer la compra")
    public List<Todo> getAllTodos(@PathVariable String name) {
        log.info("pasa x aca");
        return todoHarcodedService.findAll();
    }

    @GetMapping(value = "/users/{name}/todos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "response OK"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "brings back specific item with id as parameter")
    public ResponseEntity<Todo> findTodoByid(@PathVariable String name, @PathVariable Long id) {
        if(todoHarcodedService.findById(id).isEmpty()){
            throw new ItemNotFoundException();
        }
        return ResponseEntity.ok(todoHarcodedService.findById(id).get());
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "response OK"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "creates a new item in User'todo app list")
    public ResponseEntity<Long> addTodo(@Valid @RequestBody TodoDTO todoDTO) {
        Optional<Todo> todoInserted = todoHarcodedService.addTodo(todoDTO);
        if(todoInserted.isEmpty()){
            throw new ItemNotFoundException();
        }
        return ResponseEntity.ok(todoInserted.get().getId());
    }

    @PostMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "response OK"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Updates todo list given a specific id")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody Todo todo) {
        if(todoHarcodedService.updateById(id, todo).isEmpty()){
            throw new ItemNotFoundException();
        }
        return ResponseEntity.ok(todoHarcodedService.updateById(id, todo).get());
    }

    @DeleteMapping(value = "/users/{name}/todos/{id}")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "response OK"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Deletes an item in the todoApp list")
    public BodyBuilder deleteById(@PathVariable String name, @PathVariable Long id) {
       todoHarcodedService.deleteById(id);
       return ResponseEntity.ok();
    }

}

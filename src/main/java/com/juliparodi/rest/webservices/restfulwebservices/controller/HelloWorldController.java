package com.juliparodi.rest.webservices.restfulwebservices.controller;

import com.juliparodi.rest.webservices.restfulwebservices.model.HelloWorldBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/juli")
@CrossOrigin()
public class HelloWorldController {

    @GetMapping(value = "/hello-world", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se obtuvieron datos correctamente"),
        @ApiResponse(code = 400, message = "No se reconoce sesion"),
    })
    @ApiOperation(value = "Devuelve la informacion necesaria para hacer la compra")
    public String getHelloWorld() {
        return "Hello World";
    }

    @GetMapping(value = "/hello-world-bean", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se obtuvieron datos correctamente"),
        @ApiResponse(code = 400, message = "No se reconoce sesion"),
    })
    @ApiOperation(value = "Devuelve la informacion necesaria para hacer la compra")
    public HelloWorldBean getHelloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(value = "/hello-world/path-variable/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Se obtuvieron datos correctamente"),
        @ApiResponse(code = 400, message = "No se reconoce sesion"),
    })
    @ApiOperation(value = "Devuelve la informacion necesaria para hacer la compra")
    public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name) {

        return new HelloWorldBean("Hello World");
    }

}

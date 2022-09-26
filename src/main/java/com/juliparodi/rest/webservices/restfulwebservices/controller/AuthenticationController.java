package com.juliparodi.rest.webservices.restfulwebservices.controller;

import com.juliparodi.rest.webservices.restfulwebservices.model.Authenticationbean;
import com.juliparodi.rest.webservices.restfulwebservices.model.Todo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/")
@CrossOrigin()
public class AuthenticationController {

    @GetMapping(value = "/basicauth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "response OK"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ApiOperation(value = "Devuelve la informacion necesaria para hacer la compra")
    public Authenticationbean authenticate() {
        return new Authenticationbean("you are now authenticated");
    }

}

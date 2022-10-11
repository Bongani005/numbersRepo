package com.numbers.controller.user;


import com.numbers.entities.UserRequest;
import com.numbers.service.UserServices;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserServices services;


    @SneakyThrows
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        UserResponse response = services.save(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SingleUserResponse> getUserById(@PathVariable(value = "username") String username){
        return new ResponseEntity<>(services.getUserById(username), HttpStatus.OK);
    }

    @SneakyThrows
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody UserRequest request){
        LoginResponse response = services.login(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeletedUserResponse> getUserDeleteById(@PathVariable(value = "username") String username){
        return new ResponseEntity<>(services.deleteUser(username), HttpStatus.ACCEPTED);
    }
}

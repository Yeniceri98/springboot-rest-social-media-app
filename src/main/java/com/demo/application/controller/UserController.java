package com.demo.application.controller;

import com.demo.application.dao.UserDAOImpl;
import com.demo.application.entity.User;
import com.demo.application.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    // Dependency Injection
    private UserDAOImpl userDAOImpl;

    // Constructor Injection
    public UserController(UserDAOImpl userDAOImpl) {
        this.userDAOImpl = userDAOImpl;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDAOImpl.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public User retrieveSingleUser(@PathVariable int id) {
        User user = userDAOImpl.findSingleUserById(id);

        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " is not found!");
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userDAOImpl.save(user);
        return ResponseEntity.created(null).build();        // Status Code 201
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable int id) {
        User user = userDAOImpl.findSingleUserById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();       // Status Code 404
        }

        try {
            userDAOImpl.deleteUserById(id);
            return ResponseEntity.noContent().build();      // Status Code 204
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();    // Status Code 500
        }
    }
}

/*
    @RequestBody
    This annotation binds the POJO to a method parameter. We can access the request body as a POJO
    For example by typing (@RequestBody User user), you can access its properties like user.getName() and user.getBirthDate() methods


    ResponseEntity
    ResponseEntity represents the whole HTTP response: status code, headers, and body
    As a result, we can use it to fully configure the HTTP response
    @PostMapping kısmında gönderilen isteğin success olduğu durumda 200 değil 201 status code'u döndürmesi için ResponseEntity.created() ekledik
    200'de de düzgün çalışır fakat yeni bir şey yaratıldığında 201'in kullanılması best practice'tir
    POST requestinin success olduğu durumda Status Code'un 201 olduğu görüşürüz
*/
package com.example.spring.project.controller;

import com.example.spring.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.spring.project.service.Userservice;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@SpringBootApplication

@RestController
@RequestMapping("/api/user")

public class UserController {
    private final Userservice userservice;

    @Autowired
    public UserController(Userservice userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registeruser(@RequestBody User user) {
        String result = userservice.registeruser(user);
        if (result.equals("user registered successfully")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        String result = userservice.loginUser(email, password);

        if (result.equals("Login successful")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/list_users")
    public ResponseEntity<List<User>> listusers(){
        List<User> users = userservice.listusers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Object> getbyid(@PathVariable long id){
        Optional<User> getuser = userservice.getbyid(id);
        if (getuser.isPresent()) {
            return new ResponseEntity<>(getuser.get(), HttpStatus.OK);  // Return the user if found
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        String result = userservice.updateUser(id, updatedUser);

        if (result.equals("User updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = userservice.deleteUser(id);

        if (result.equals("User deleted successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}

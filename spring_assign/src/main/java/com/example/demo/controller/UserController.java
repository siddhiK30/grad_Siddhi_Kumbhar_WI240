
package com.example.demo.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.db1.entity.User;
import com.example.demo.db1.repo.*;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {

    @Autowired
    UserDAO repo;

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody User user){

    Optional<User> u = repo.findByUsername(user.getUsername());

    if(u.isPresent() &&
       u.get().getPassword().equals(user.getPassword()) &&
       u.get().getRole().equals("ADMIN")){

        return ResponseEntity.ok(u.get());
    }

    return ResponseEntity.status(401).body("Access denied");
}
}
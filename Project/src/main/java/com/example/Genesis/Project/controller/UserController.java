package com.example.Genesis.Project.controller;

import com.example.Genesis.Project.model.User;
import com.example.Genesis.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "detail", defaultValue = "false") boolean detail) {
        return ResponseEntity.ok(userService.getAllUsers(detail));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUserById(@RequestParam(value = "detail", defaultValue = "false") boolean detail, @PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id, detail));
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }
    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.updateUser(user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

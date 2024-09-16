package com.user.crud.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.crud.pojo.User;
import com.user.crud.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	Logger logger = Logger.getLogger(UserController.class.getName());
	
	@Autowired
    private UserService userService;

	public UserController() { }
	
	// Create a new user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
    	logger.log(Level.INFO, "Creating user with userName:" + user.getUsername());
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Read a user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> readUser(@PathVariable String username) {
    	logger.log(Level.INFO, "Fatching user details for userName:" + username);
        User user = userService.readUser(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update an existing user
    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User updatedUser) {
    	logger.log(Level.INFO, "Updating user details for userName:" + username);
        try {
            userService.updateUser(username, updatedUser);
            return ResponseEntity.ok("User updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Delete a user by username
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
    	logger.log(Level.INFO, "Removing user details for userName:" + username);
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("User deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}


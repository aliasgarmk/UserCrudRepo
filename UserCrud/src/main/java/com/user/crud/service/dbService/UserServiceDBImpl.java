package com.user.crud.service.dbService;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.crud.jpa.UserRepository;
import com.user.crud.pojo.User;
import com.user.crud.service.UserService;
import com.user.crud.service.redisService.UserServiceRadisImpl;

@Service
public class UserServiceDBImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
   
    Logger logger = Logger.getLogger(UserServiceDBImpl.class.getName());

    // Create a new user
    @Override
    public void createUser(User user) {
    	logger.log(Level.INFO, "Creating user details into inMemory DB");
        if (userRepository.existsById(user.getUsername())) {
            throw new IllegalArgumentException("User already exists.");
        }
        userRepository.save(user);
    }

    // Read a user by username
    @Override
    public User readUser(String username) {
    	logger.log(Level.INFO, "Fetching user details from inMemory DB");
    	User user = userRepository.findByUsername(username);
        
        return user;
    }

    // Update an existing user
    @Override
    public void updateUser(String username, User updatedUser) {
    	logger.log(Level.INFO, "Updating user details into inMemory DB");
		if (!userRepository.existsById(username)) {
            throw new IllegalArgumentException("User does not exist.");
        }
        userRepository.save(updatedUser);
    }

    // Delete a user by username
    @Transactional
    @Override
    public void deleteUser(String username) {
    	logger.log(Level.INFO, "Deleting user details from inMemory DB");
		if (!userRepository.existsById(username)) {
            throw new IllegalArgumentException("User does not exist.");
        }
        userRepository.deleteById(username);
    }
}


package com.user.crud.service;

import org.springframework.stereotype.Service;

import com.user.crud.pojo.User;

import jakarta.transaction.Transactional;

@Service
public interface UserService {

	@Transactional
	public void createUser(User user);
	
	public User readUser(String username);
	
	@Transactional
    public void updateUser(String username, User updatedUser);
	
	@Transactional
    public void deleteUser(String username);
	
	
}

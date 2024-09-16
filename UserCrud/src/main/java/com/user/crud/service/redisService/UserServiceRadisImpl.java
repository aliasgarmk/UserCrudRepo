package com.user.crud.service.redisService;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.user.crud.controller.UserController;
import com.user.crud.pojo.User;
import com.user.crud.service.UserService;
import com.user.crud.service.adapter.UserToJsonAdapter;

public class UserServiceRadisImpl implements UserService {

	Logger logger = Logger.getLogger(UserServiceRadisImpl.class.getName());
	
	private static final String REDIS_KEY_PREFIX = "user:";

	@Autowired
    private StringRedisTemplate redisTemplate;
	
	@Autowired
	private UserToJsonAdapter adapter;

    @Override
	public void createUser(User user) {
    	logger.log(Level.INFO, "Creating user details into redis");
        cacheUser(user);
	}

	@Override
	public User readUser(String username) {
		logger.log(Level.INFO, "Fetching user details from redis");
		User user = getUserFromCache(username);
		return user;
	}

	@Override
	public void updateUser(String username, User updatedUser) {
		logger.log(Level.INFO, "Updating user details into redis");
		cacheUser(updatedUser);
	}

	@Override
	public void deleteUser(String username) {
		logger.log(Level.INFO, "Deleting user details from redis");
		redisTemplate.delete(REDIS_KEY_PREFIX + username);
	}

    private User getUserFromCache(String username) {
        String userJson = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + username);
        if (userJson != null) {
            // Convert JSON back to User object
            return adapter.convertJsonToUser(userJson);
        }
        return null;
    }

    private void cacheUser(User user) {
        // Convert User object to JSON
        String userJson = adapter.convertUserToJson(user);
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + user.getUsername(), userJson);
    }
	
	

}

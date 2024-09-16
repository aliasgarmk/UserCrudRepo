package com.user.crud.service.adapter;

import org.springframework.stereotype.Service;

import com.user.crud.pojo.User;

@Service
public class UserToJsonAdapter {
	
	public String convertUserToJson(User user) {
        // Simple conversion; use a library like Jackson or Gson for real-world applications
        return String.format("{\"username\":\"%s\",\"password\":\"%s\",\"active\":%b}", 
                             user.getUsername(), user.getPassword(), user.isActive());
    }

    public User convertJsonToUser(String userJson) {
        // Simple conversion; use a library like Jackson or Gson for real-world applications
        String[] parts = userJson.replace("{", "").replace("}", "").split(",");
        String username = parts[0].split(":")[1].replace("\"", "");
        String password = parts[1].split(":")[1].replace("\"", "");
        boolean active = Boolean.parseBoolean(parts[2].split(":")[1]);
        return new User(username, password, active);
    }
}

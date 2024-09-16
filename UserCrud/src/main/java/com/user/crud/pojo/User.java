package com.user.crud.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "UserDetails")
public class User {
    @Id
    private String username;
    private String password; // Alphanumeric password
    private boolean active;

    public User() {
        // Default constructor for JPA
    }

    public User(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", active=" + active +
               '}';
    }
}


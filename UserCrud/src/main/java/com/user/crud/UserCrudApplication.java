package com.user.crud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.user.crud.service.UserService;
import com.user.crud.service.dbService.UserServiceDBImpl;
import com.user.crud.service.redisService.UserServiceRadisImpl;

@SpringBootApplication
@Configuration
public class UserCrudApplication {
	
	@Value("${radis.enable}")
	private boolean enableRadis;

	public static void main(String[] args) {
		SpringApplication.run(UserCrudApplication.class, args);
	}

	@Bean
	public UserService userService() {
		if(enableRadis) {
			return new UserServiceRadisImpl();
		} else {
			return new UserServiceDBImpl();
		}
	}
}

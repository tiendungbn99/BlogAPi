package com.ltw.blog.services;


import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ltw.blog.entities.User;
import com.ltw.blog.payload.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deletesUser(Integer userId);
	
	Optional<User> findByUsername(String username);

}

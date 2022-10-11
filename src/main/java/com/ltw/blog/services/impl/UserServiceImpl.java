package com.ltw.blog.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ltw.blog.entities.User;
import com.ltw.blog.exceptions.ResourceNotFoundException;
import com.ltw.blog.payload.UserDto;
import com.ltw.blog.repositories.UserRepo;
import com.ltw.blog.security.UserPrincipal;
import com.ltw.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User saveUser=this.userRepo.save(user);
		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setFullName(userDto.getFullName());
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser =this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updateUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()
				-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deletesUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->
			new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		
	}
	public User dtoToUser( UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepo.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrincipal.build(userOptional.get());
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}

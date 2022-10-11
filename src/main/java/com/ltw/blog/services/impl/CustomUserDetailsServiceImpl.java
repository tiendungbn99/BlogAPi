package com.ltw.blog.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ltw.blog.entities.User;
import com.ltw.blog.repositories.UserRepo;
import com.ltw.blog.security.UserPrincipal;
import com.ltw.blog.services.CustomUserDetailsService;

public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with this username or email: %s", username)));
		return UserPrincipal.build(user);
	}

	@Override
	public UserDetails loadUserById(Integer id) {
		User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with id: %s", id)));

		return UserPrincipal.build(user);
	}

}

package com.ltw.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltw.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(Role name);
}
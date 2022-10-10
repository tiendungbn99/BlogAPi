package com.ltw.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltw.blog.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}

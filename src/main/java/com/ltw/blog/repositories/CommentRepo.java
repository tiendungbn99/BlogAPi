package com.ltw.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltw.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}

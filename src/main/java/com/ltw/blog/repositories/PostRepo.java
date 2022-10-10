package com.ltw.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ltw.blog.entities.Category;
import com.ltw.blog.entities.Post;
import com.ltw.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findAllByUser(User user);
	List<Post> findAllByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	
}

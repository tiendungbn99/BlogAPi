package com.ltw.blog.services;

import java.util.List;

import com.ltw.blog.entities.Post;
import com.ltw.blog.payload.PostDto;
import com.ltw.blog.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostDto getPostById(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
	
	List<PostDto> getPostByCategori(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> searchPost(String keyword);
	
	

}

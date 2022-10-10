package com.ltw.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ltw.blog.entities.Category;
import com.ltw.blog.entities.Post;
import com.ltw.blog.entities.User;
import com.ltw.blog.exceptions.ResourceNotFoundException;
import com.ltw.blog.payload.PostDto;
import com.ltw.blog.payload.PostResponse;
import com.ltw.blog.repositories.CategoryRepo;
import com.ltw.blog.repositories.PostRepo;
import com.ltw.blog.repositories.UserRepo;
import com.ltw.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId,Integer categoryId) {
		User user =this.userRepo.findById(userId).orElseThrow(()->
			new ResourceNotFoundException("User", "Id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->
			new ResourceNotFoundException("Category", "Id", categoryId));
		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost = this.postRepo.save(post);
		return this.modelmapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatePost=this.postRepo.save(post);
		return this.modelmapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post", "Id", postId));
		this.postRepo.delete(post);	
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post", "Id", postId));
		PostDto postDto= this.modelmapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPost = pagePost.getContent();
		List<PostDto> postDtos= allPost.stream().map(
				(post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPage(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategori(Integer categoryId) {
		Category cat= this.categoryRepo.findById(categoryId).orElseThrow(()
				-> new ResourceNotFoundException("Category", "Id", categoryId));
		List<Post> posts = this.postRepo.findAllByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)
				->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user =this.userRepo.findById(userId).orElseThrow(()
				-> new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts= this.postRepo.findAllByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)
				->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos= posts.stream().map(
				(post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}

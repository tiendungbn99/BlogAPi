package com.ltw.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltw.blog.entities.Comment;
import com.ltw.blog.entities.Post;
import com.ltw.blog.exceptions.ResourceNotFoundException;
import com.ltw.blog.payload.CommentDto;
import com.ltw.blog.repositories.CommentRepo;
import com.ltw.blog.repositories.PostRepo;
import com.ltw.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto,Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("Post", "Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment addComment=this.commentRepo.save(comment); 
		return this.modelMapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer comId) {
		Comment comment = this.commentRepo.findById(comId).orElseThrow(
				()->new ResourceNotFoundException("Comment", "Id", comId));
		this.commentRepo.delete(comment);
	}

}

package com.ltw.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltw.blog.payload.ApiResponse;
import com.ltw.blog.payload.CommentDto;
import com.ltw.blog.services.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}")
	public ResponseEntity<CommentDto> createComment(
			@RequestBody CommentDto commentDto,
			@PathVariable Integer postId){
		CommentDto addComment = this.commentService.createComment(commentDto, postId);
				return new ResponseEntity<CommentDto>(addComment,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/delete/{comId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer comId){
		this.commentService.deleteComment(comId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment delete successfully",true),HttpStatus.OK);
	}
}

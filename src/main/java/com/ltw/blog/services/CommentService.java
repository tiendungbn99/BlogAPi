package com.ltw.blog.services;

import com.ltw.blog.payload.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer comId);

}

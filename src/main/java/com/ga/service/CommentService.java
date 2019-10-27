package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.exceptionhandling.DeleteException;

public interface CommentService {
	public Long deleteComment(String username, Long commentId) throws DeleteException;
	public List<Comment> getAllCommentByUserName(String username);
}

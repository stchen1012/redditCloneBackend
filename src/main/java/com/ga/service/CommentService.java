package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;

public interface CommentService {
	public Long deleteComment(Long commentId);
	public List<Comment> getAllCommentByUserName(String username);
}

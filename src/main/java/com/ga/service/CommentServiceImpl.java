package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import com.ga.exceptionhandling.DeleteException;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;

	@Override
	public Long deleteComment(String username, Long commentId) throws DeleteException {
		return commentDao.deleteComment(username, commentId);
	}

	@Override
	public List<Comment> getAllCommentByUserName(String username) {
		return commentDao.getAllCommentByUserName(username);
	}
	
	
}

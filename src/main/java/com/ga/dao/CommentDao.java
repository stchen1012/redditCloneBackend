package com.ga.dao;

import java.util.List;
import com.ga.entity.Comment;
import com.ga.exceptionhandling.DeleteException;

public interface CommentDao {
	public Long deleteComment(String username, Long commentId) throws DeleteException;
	public List<Comment> getAllCommentByUserName(String username);
}

package com.ga.dao;

import java.util.List;
import com.ga.entity.Comment;

public interface CommentDao {
	public Long deleteComment(Long commentId);
	public List<Comment> getAllCommentByUserName(String username);
}

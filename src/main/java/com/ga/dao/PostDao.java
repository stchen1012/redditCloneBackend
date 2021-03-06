package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.exceptionhandling.DeleteException;

public interface PostDao {

	public List<Post> getAllPosts();
	public List<Post> getAllPostByUsername(String username);
	public List<Comment> getCommentsByPostId(Long postId);
	public Long deletePost(String username, Long postId) throws DeleteException;
}

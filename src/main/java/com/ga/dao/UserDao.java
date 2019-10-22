package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public interface UserDao {

//	public List<User> listUsers();

	public User signup(User user);

	public User login(User user);

	public User getUserByUsername(String username);
	
	public User addPost(String username, Post post);
	
	public User addComment(String username, Long postId, Comment comment);

	public Post getPostById(long postId);

}

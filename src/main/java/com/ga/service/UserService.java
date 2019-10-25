package com.ga.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public interface UserService extends UserDetailsService {

	public String signup(User user);

	public String login(User user);
	
	public User addPost(String username, Post post);
	
	public User addComment(String username, Long postId, Comment comment);

}

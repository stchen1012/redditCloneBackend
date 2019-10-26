package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.config.JwtUtil;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	JwtUtil	jwtUtil;
	
	//TODO - userId is ignored, update  to show userID when loading
	@GetMapping("/list")
	public List<Post> getAllPosts(){
		return postService.getAllPosts();
	}
	
	
	@GetMapping("/list/{username}")
	public List<Post> getAllPostByUsername(@PathVariable String username) {
		return postService.getAllPostByUsername(username);
	}
	
	
	@GetMapping("/{postId}/comments")
	public List<Comment> getCommentsByPostId(@PathVariable Long postId){
		return postService.getCommentsByPostId(postId);
	}

	@DeleteMapping("/remove/{postId}")
	public Long deletePost(@PathVariable Long postId) {
		return postService.deletePost(postId);
	}
}

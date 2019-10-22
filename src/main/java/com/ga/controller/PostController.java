package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService postService;
	
	@GetMapping("/list")
	public List<Post> getAllPosts(){
		return postService.getAllPosts();
	}
	
	@GetMapping("/list/{userId}")
	public List<Post> getAllPostByUserId(@PathVariable Long userId){
		return postService.getAllPostByUserId(userId);
	}
	
	@GetMapping("/{postId}/comments")
	public List<Comment> getCommentsByPostId(@PathVariable Long postId){
		return postService.getCommentsByPostId(postId);
	}
	
	@DeleteMapping("/{postId}")
	public Post deletePost(@PathVariable Long postId) {
		return postService.deletePost(postId);
	}
}

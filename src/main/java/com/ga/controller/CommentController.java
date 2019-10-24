package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@DeleteMapping("/{commentId}")
	public Long deleteComment(@PathVariable Long commentId) {
		return commentService.deleteComment(commentId);
	}
	
	@GetMapping("/list/{username}")
	public List<Comment> getAllPostByUserId(@PathVariable String username){
		return commentService.getAllCommentByUserName(username);
	}
	

}

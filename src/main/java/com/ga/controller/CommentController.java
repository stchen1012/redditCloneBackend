package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.service.CommentService;
import com.ga.service.PostService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@DeleteMapping("/{commentId}")
	public Long deleteComment(@PathVariable Long commentId) {
		return commentService.deleteComment(commentId);
	}
}

package com.ga.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.config.JwtUtil;
import com.ga.entity.Comment;
import com.ga.exceptionhandling.DeleteException;
import com.ga.exceptionhandling.ErrorResponse;
import com.ga.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@DeleteMapping("/remove/{commentId}")
	public Long deleteComment(@RequestHeader("Authorization") String headerToken, @PathVariable Long commentId) {
		return commentService.deleteComment(getUserNameFromToken(headerToken), commentId);
	}
	
	@GetMapping("/list/{username}")
	public List<Comment> getAllPostByUserId(@PathVariable String username){
		return commentService.getAllCommentByUserName(username);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleExcption(DeleteException err){
		ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), err.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	// helper method to get username from token
	private String getUserNameFromToken(String token) {
		List<String> header = Arrays.asList(token.split(" "));
		return jwtUtil.getUsernameFromToken(header.get(1));
	}
}

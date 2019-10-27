package com.ga.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.config.JwtUtil;
import com.ga.entity.Comment;
import com.ga.entity.JwtResponse;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exceptionhandling.ErrorResponse;
import com.ga.exceptionhandling.IncorrectLoginException;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
        	return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
    	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
	}
	
	@PostMapping("/post")
	public User addPost(@RequestHeader("Authorization") String headerToken, @RequestBody Post post) {
		return userService.addPost(getUserNameFromToken(headerToken), post);
	}
	
	@PostMapping("/{postId}/comment")
	public User addComment(@RequestHeader("Authorization") String headerToken, @PathVariable Long postId, @RequestBody Comment comment) {
		return userService.addComment(getUserNameFromToken(headerToken), postId, comment);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleExcption(IncorrectLoginException err){
		ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), err.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
	}
	
	// helper method to get username from token
	private String getUserNameFromToken(String token) {
		List<String> header = Arrays.asList(token.split(" "));
		return jwtUtil.getUsernameFromToken(header.get(1));
	}
}

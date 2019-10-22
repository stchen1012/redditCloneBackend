package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.JwtResponse;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/list")
//	public List<User> listUsers() {
//		return userService.listUsers();
//	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user) {
        	return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
    	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
	}
	
	@PostMapping("/{username}/post")
	public User addPost(@PathVariable String username, @RequestBody Post post) {
		return userService.addPost(username, post);
	}
}

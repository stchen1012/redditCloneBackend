package com.ga.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.config.JwtUtil;
import com.ga.entity.UserProfile;
import com.ga.service.UserProfileService;

@RestController
@RequestMapping("/profile")
public class UserProfileController {


	private UserProfileService userProfileService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
	
    @GetMapping
    public UserProfile getUserProfile(@RequestHeader("Authorization") String headerToken) {
    	System.out.println(headerToken);
        return userProfileService.getUserProfile(getUserNameFromToken(headerToken));
    }
    
    @PostMapping
    public UserProfile addUserProfile(@RequestHeader("Authorization") String headerToken, @RequestBody UserProfile userProfile) {
        return userProfileService.addUserProfile(getUserNameFromToken(headerToken), userProfile);
    }
    
    @PatchMapping
    public UserProfile updateUserProfile(@RequestHeader("Authorization") String headerToken, @RequestBody UserProfile updateProfile) {
        return userProfileService.updateUserProfile(getUserNameFromToken(headerToken), updateProfile);
    }
    
	// helper method to get username from token
	private String getUserNameFromToken(String token) {
		List<String> header = Arrays.asList(token.split(" "));
		System.out.println("TEST" + jwtUtil.getUsernameFromToken(header.get(1)));
		return jwtUtil.getUsernameFromToken(header.get(1));
	}

}

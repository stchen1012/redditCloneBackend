package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.UserProfile;
import com.ga.service.UserProfileService;

@RestController
@RequestMapping("/profile")
public class UserProfileController {


	private UserProfileService userProfileService;
	
    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }
	
    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable String username) {
        return userProfileService.getUserProfile(username);
    }
    
    @PostMapping("/{username}")
    public UserProfile addUserProfile(@PathVariable String username, @RequestBody UserProfile userProfile) {
        return userProfileService.addUserProfile(username, userProfile);
    }
    
    @PatchMapping("/{username}")
    public UserProfile updateUserProfile(@PathVariable String username, @RequestBody UserProfile updateProfile) {
        return userProfileService.updateUserProfile(username, updateProfile);
    }

}

package com.ga.service;

import com.ga.entity.UserProfile;

public interface UserProfileService {
	public UserProfile addUserProfile(String username, UserProfile newProfile);

	public UserProfile updateUserProfile(String username, UserProfile updateProfile);

	public UserProfile getUserProfile(String username);
}

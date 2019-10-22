package com.ga.dao;

import com.ga.entity.UserProfile;

public interface UserProfileDao {
	public UserProfile addUserProfile(String username, UserProfile newProfile);
	
	public UserProfile updateUserProfile(String username, UserProfile updateProfile);

	public UserProfile getUserProfile(String username);
}

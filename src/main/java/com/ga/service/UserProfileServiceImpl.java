package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.UserProfileDao;
import com.ga.entity.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService {


	private UserProfileDao userProfileDao;
    
    @Autowired
    public UserProfileServiceImpl(UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }
    
	@Override
	public UserProfile addUserProfile(String username, UserProfile newProfile) {
		return userProfileDao.addUserProfile(username, newProfile);
	}

	@Override
	public UserProfile getUserProfile(String username) {
		return userProfileDao.getUserProfile(username);	
	}


	@Override
	public UserProfile updateUserProfile(String username, UserProfile updateProfile) {
		return userProfileDao.updateUserProfile(username, updateProfile);
	}

}

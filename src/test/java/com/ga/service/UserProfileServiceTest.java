package com.ga.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.dao.UserProfileDao;
import com.ga.entity.Post;
import com.ga.entity.UserProfile;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTest {
	
	private UserProfile newUserProfile;
	
	@InjectMocks
	UserProfileServiceImpl userProfileService;
	
	@InjectMocks
	UserProfile userProfile;
	
	@Mock
	UserProfileDao userProfileDao;
	
	@Before
	public void initialize() {
		userProfile = new UserProfile();
		userProfile.setProfileId(1L);
		userProfile.setEmail("batman@batman.com");
		userProfile.setMobile("111-111-1111");
		userProfile.setAddress("Gotham City");
	}
	
	@Test
	public void addUserProfile_UserProfile_SUCCESS(){
		when(userProfileDao.addUserProfile("someUser", userProfile)).thenReturn(userProfile);
		newUserProfile = userProfileService.addUserProfile("someUser", userProfile);
		assertEquals(newUserProfile.getEmail(), userProfile.getEmail());
	}
	
	@Test
	public void getUserProfile_UserProfile_SUCCESS() {
		when(userProfileDao.getUserProfile("someUser")).thenReturn(userProfile);
		newUserProfile = userProfileService.getUserProfile("someUser");
		assertEquals(newUserProfile.getEmail(), userProfile.getEmail());
	}
}

package com.ga.dao;

import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import com.ga.entity.User;
import com.ga.entity.UserProfile;

public class UserProfileDaoTest {
	
	private UserProfile userP;
	private User user;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	private UserProfileDaoImpl userProfileDao;
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private Session session;
	
	@Mock
	private Transaction transaction;

	
	@Before
	public void initialize() {
		user = new User();
		userP = new UserProfile();
		userP.setProfileId(1L);
		userP.setAddress("111");
		userP.setEmail("email");
		userP.setMobile("12345");
        user.setUserId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
        user.setUserProfile(userP);
        
		when(sessionFactory.getCurrentSession()).thenReturn(session);
	    when(session.getTransaction()).thenReturn(transaction);
	}
	
	@Test
	public void getUserProfile_UserProfile_SUCCESS() {
		when(userDao.getUserByUsername(any())).thenReturn(user);
		
		UserProfile tempUserProfile = userProfileDao.getUserProfile(user.getUsername());
		
    	assertNotNull("Tested returned null obj, expected not null", tempUserProfile);
    	assertEquals(tempUserProfile.getEmail(), userP.getEmail());
	}
	
	@Test
	public void addUserProfile_UserProfile_SUCCESS() {
		when(userDao.getUserByUsername(any())).thenReturn(user);
		
		UserProfile tempUserProfile = userProfileDao.addUserProfile("someUser", userP);
		
    	assertNotNull("Tested returned null obj, expected not null", tempUserProfile);
    	assertEquals(tempUserProfile.getEmail(), userP.getEmail());
	}
	
	@Test
	public void updateUserProfile_UserProfile_SUCCESS() {
		UserProfile updatedInfo = new UserProfile();
		updatedInfo.setEmail("newEmail@Email.com");
		updatedInfo.setAddress(null);
		updatedInfo.setMobile(null);
		
		when(userDao.getUserByUsername(any())).thenReturn(user);
		when(session.get(any(Class.class), any())).thenReturn(userP);
		
		UserProfile tempUserProfile = userProfileDao.updateUserProfile("someUser", updatedInfo);
		
    	assertNotNull("Tested returned null obj, expected not null", tempUserProfile);
    	assertEquals("newEmail@Email.com", tempUserProfile.getEmail());
		
	}
	
}

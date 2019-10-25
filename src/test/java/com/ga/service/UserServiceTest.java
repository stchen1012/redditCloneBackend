package com.ga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.entity.UserProfile;

public class UserServiceTest {

	@Mock
	UserDao userDao;
	
	@Mock
	UserProfile userProfile;
	
	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private PasswordEncoder bCryptPasswordEncoder;

	@InjectMocks
	private User user;

	@InjectMocks
	private UserServiceImpl userService;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		user.setUserId(1L);
		user.setPassword("123");
		user.setUsername("batman");
		
		userProfile.setAddress("124");
		userProfile.setEmail("aa@aa.com");
		userProfile.setMobile("333-333-3333");
		user.setUserProfile(userProfile);		
	}

	@Test
	public void signup_String_SUCCESS() {
		String expectedToken = "12345";

		when(userDao.signup(any())).thenReturn(user);
		when(userDao.getUserByUsername(anyString())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
		when(bCryptPasswordEncoder.encode(any())).thenReturn("robin");
		System.out.println(user.getUsername());
		String actualToken = userService.signup(user);

		assertEquals(actualToken, expectedToken);
	}
	
	@Test
    public void login_User_Success() {
        String expectedToken = "12345";
        
        when(userDao.login(any())).thenReturn(user);
        when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");
        
        String actualToken = userService.login(user);
        assertEquals(actualToken, expectedToken);
        
    }
}

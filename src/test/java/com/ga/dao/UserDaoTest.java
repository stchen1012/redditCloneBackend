package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.User;

public class UserDaoTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@InjectMocks
	private User user;
	
	
	@InjectMocks
	private UserDaoImpl userDao;
	
	@Mock
	private SessionFactory sessionFactory;
	
	@Mock
	private Session session;
	
	@Mock
	private Transaction transaction;
	
	@Mock
    Query<User> query;
	
	@Before
    public void init() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
	    when(session.getTransaction()).thenReturn(transaction);
	}
    
    @Before
    public void initializeDummyUser() {
        
        user.setUserId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
    }
    
    @Test
    public void signup_User_Success() {
    	User tempUser = userDao.signup(user);
        
        assertNotNull(tempUser);
        
        assertEquals(tempUser.getUsername(), user.getUsername());
    }
    
    @Test
    public void login_User_Success() {
    	when(session.createQuery(anyString())).thenReturn(query);
    	when(query.getSingleResult()).thenReturn(user);
        
        User savedUser = userDao.login(user);
        
        assertNotNull("Test returned null object, expected non-null", savedUser);
        assertEquals(savedUser, user);
    }
    
}
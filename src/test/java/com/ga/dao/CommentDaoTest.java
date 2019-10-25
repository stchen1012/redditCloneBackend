package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public class CommentDaoTest {
	
	private List<Comment> comments;
	private Comment comment;
	
	@InjectMocks
    private User user;

    @InjectMocks
    CommentDaoImpl commentDao;
    
    @Mock
    private SessionFactory sessionFactory;
    
    @Mock
    Query<User> query;

    @Mock
    Query<Comment> queryComment;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @Before
    public void initializer() {
    	comment = new Comment();
    	comments = new ArrayList<Comment>();
        
    	comment.setCommentId(1L);
    	comment.setText("some post");
    	comments.add(comment);
    	
    	user.setUserId(1L);
        user.setUsername("testuser");
        user.setPassword("testpass");

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }
	
    @Test
    public void deleteComment_Long_SUCCESS() {
    	when(session.createQuery(anyString())).thenReturn(query, queryComment);
    	when(query.uniqueResult()).thenReturn(user);
    	when(queryComment.getResultList()).thenReturn(comments);
    	
    	List<Comment> commentByUser = commentDao.getAllCommentByUserName("someUser");
    	
    	assertNotNull("Tested returned null obj, expected not null",comment);
    	assertEquals(comment.getText(), commentByUser.get(0).getText());
    }
    
    @Test
    public void getAllCommentByUserName_Posts_SUCCESS() {
    	
    }

}

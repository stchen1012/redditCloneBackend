package com.ga.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import com.ga.entity.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class CommentServiceTest {

	private Comment comment;
	private List<Comment> comments;
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@InjectMocks
	private User user;
	
	@InjectMocks
	CommentServiceImpl commentService;
	
	@Mock
	CommentDao commentDao;
	
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
    }

    @Test
    public void deleteComment_Long_SUCCESS() {
    	when(commentDao.deleteComment(any(), any())).thenReturn(1L);
    	Long tempId = commentService.deleteComment("someuser", 1L);
    	assertEquals(tempId, comment.getCommentId());
    }
    
    @Test
    public void getAllCommentByUserName_Comments_SUCCESS() {
    	when(commentDao.getAllCommentByUserName(any())).thenReturn(comments);
    	List<Comment> myComments = commentService.getAllCommentByUserName("someUesr");
    	assertEquals(comments, myComments);
    }

}

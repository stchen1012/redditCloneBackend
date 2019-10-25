package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
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



public class PostDaoTest {
	
	private List<Post> posts;
	private Post post;
	private List<Comment> comments;
	private Comment comment;
	
	@InjectMocks
    private User user;


    @InjectMocks
    PostDaoImpl postDao;
    
    @Mock
    private SessionFactory sessionFactory;

    
    @Mock
    Query<User> query;
    
    @Mock
    Query<Post> queryPost;
        
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    
    @Before
    public void initializer() {
    	post = new Post();
    	posts = new ArrayList<Post>();
    	comment = new Comment();
    	comments = new ArrayList<Comment>();
        
    	comment.setCommentId(1L);
    	comment.setText("some post");
    	comments.add(comment);
    	
    	user.setUserId(1L);
        user.setUsername("testuser");
        user.setPassword("testpass");
        
        post.setPostId(1L);
        post.setTitle("some title");
        post.setDescription("some description");
        post.setComments(comments);
        
        posts.add(post);
        
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Test
    public void getAllPosts_Posts_SUCCESS() {
    	when(session.createQuery(anyString())).thenReturn(queryPost);
    	when(queryPost.getResultList()).thenReturn(posts);
    	
    	List<Post> allPosts = postDao.getAllPosts();
    	
    	assertNotNull("Tested returned null obj, expected not null", allPosts);
    	assertEquals(post.getTitle(), allPosts.get(0).getTitle());
    }
	
	@Test
	public void getAllPostByUsername_Posts_SUCCESS() {
    	when(session.createQuery(anyString())).thenReturn(query, queryPost);
    	when(query.getSingleResult()).thenReturn(user);
    	when(queryPost.getResultList()).thenReturn(posts);
    	
    	List<Post> postByUser = postDao.getAllPostByUsername("someUser");
    	
    	assertNotNull("Tested returned null obj, expected not null", postByUser);
    	assertEquals(post.getTitle(), postByUser.get(0).getTitle());
    
	}
	
	@Test
	public void getCommentsByPostId_Comments_SUCCESS() {
		when(session.get(any(Class.class), any())).thenReturn(post);
		List<Comment> postById = postDao.getCommentsByPostId(1L);
		assertNotNull("Tested returned null obj, expected not null", postById);
		assertEquals(postById.get(0).getText(), comment.getText());
	}
	
	@Test
	public void deletePost_PostId_SUCCESS() {
		when(session.get(any(Class.class), any())).thenReturn(post);
		
		Long deletedPost = postDao.deletePost(1L);
		
		assertEquals(new Long(1), deletedPost);
	}
	
}

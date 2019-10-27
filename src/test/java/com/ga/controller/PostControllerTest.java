package com.ga.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ga.config.JwtUtil;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exceptionhandling.DeleteException;
import com.ga.service.PostService;
import com.ga.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
	
	private MockMvc mockMvc;
	private String username;
	private User user;
	private Post post;
	private List<Post> posts;
	private Comment comment;
	private List<Comment> comments;
	
    
    @InjectMocks
    PostController postController;
    
	@Mock
	PostService postService;
	
	@Mock
	JwtUtil jwtUtil;
	
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    } 
    
    @Before
    public void initializer() {
    	user = new User();
    	username = "someUser";
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
    }
    
    @Test
    public void getAllPosts_Posts_SUCCESS() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.get("/post/list")
    			.accept(MediaType.APPLICATION_JSON);

    	when(postService.getAllPosts()).thenReturn(posts);
    	
    	mockMvc.perform(requestBuilder)
    		.andExpect(status().isOk());
//    		.andReturn();
    }
    
    @Test
    public void getAllPostByUsername_Posts_SUCCESS() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.get("/post/list/{username}", username)
    			.accept(MediaType.APPLICATION_JSON);

    	when(postService.getAllPostByUsername(any())).thenReturn(posts);
    	
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
    		.andExpect(status().isOk())
    		.andReturn();		    	
    }
    
    @Test
    public void getCommentsByPostId_Comments_SUCCESS() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.get("/post/{postId}/comments", 1L)
    			.accept(MediaType.APPLICATION_JSON);
    	
    	when(postService.getCommentsByPostId(any())).thenReturn(comments);  	
    	
    	MvcResult result = mockMvc.perform(requestBuilder)
        		.andExpect(status().isOk())
        		.andReturn();		    	
    }
    
    @Test
    public void deletePost_Long_SUCCESS() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.delete("/post/remove/{postId}", 1)
    			.header("Authorization", "Bearer 1234")
    			.contentType(MediaType.APPLICATION_JSON);
    	
    	when(postService.deletePost(any(), any())).thenReturn(1L);
    	when(jwtUtil.getUsernameFromToken(any())).thenReturn("someUser");
		
    	MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andReturn();    	
    }
    
    @Test
    public void deletePost_Long_401FAIL() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.delete("/post/remove/{postId}", 1)
    			.header("Authorization", "Bearer 1234")
    			.contentType(MediaType.APPLICATION_JSON);
    	
    	when(jwtUtil.getUsernameFromToken(any())).thenReturn("someUser");
    	when(postService.deletePost(any(), any())).thenThrow(new DeleteException("Unauthorized to delete this post"));

		
    	MvcResult result = mockMvc.perform(requestBuilder)
    			.andExpect(status().isUnauthorized())
	              .andReturn();    	
    }
    
    
}

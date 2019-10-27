package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import static org.mockito.ArgumentMatchers.anyString;

import com.ga.config.JwtUtil;
import com.ga.entity.Comment;
import com.ga.exceptionhandling.DeleteException;
import com.ga.service.CommentService;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
	
	private MockMvc mockMvc;
	private List<Comment> comments;
	
	@InjectMocks
	CommentController commentController;
	
	@Mock
	CommentService commentService;
	
	@Mock
	JwtUtil jwtUtil;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
	}
	
	@Before
	public void initialize() {
		comments = new ArrayList<Comment>();
	}
	
	@Test
    public void deleteComment_Long_SUCCESS() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.delete("/comment/remove/{commentId}", 1)
    			.header("Authorization", "Bearer 1234")
    			.contentType(MediaType.APPLICATION_JSON);
    	
    	when(commentService.deleteComment(any(), any())).thenReturn(1L);
		
    	MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andReturn();    	
    }
	
	@Test
    public void deleteComment_Long_401Unauthorized() throws Exception {
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
    			.delete("/comment/remove/{commentId}", 1)
    			.header("Authorization", "Bearer 1234")
    			.contentType(MediaType.APPLICATION_JSON);
    	
       	when(jwtUtil.getUsernameFromToken(any())).thenReturn("someUser");
    	when(commentService.deleteComment(any(), any())).thenThrow(new DeleteException("Unauthorized to delete this comment"));
		
    	MvcResult result = mockMvc.perform(requestBuilder)
    			.andExpect(status().isUnauthorized())
	              .andReturn();    		
    }
	
	 @Test
	    public void getCommentsByPostId_Comments_SUCCESS() throws Exception {
	    	RequestBuilder requestBuilder = MockMvcRequestBuilders
	    			.get("/comment/list/{username}", "someUser")
	    			.accept(MediaType.APPLICATION_JSON);
	    	
	    	when(commentService.getAllCommentByUserName(anyString())).thenReturn(comments);  	
	    	
	    	MvcResult result = mockMvc.perform(requestBuilder)
	        		.andExpect(status().isOk())
	        		.andReturn();		    	
	    }
	
}

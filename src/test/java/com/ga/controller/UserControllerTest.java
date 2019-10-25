package com.ga.controller;

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

import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.entity.UserProfile;
import com.ga.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;
	private User user;
	private Post post;

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Before
	public void initializeUser() {
		user = new User();
        
		user.setUsername("test");;
	}
	
	@Before
	public void initializePost() {
		post = new Post();
        
		post.setPostId((long) 1);
		post.setTitle("title");
		post.setDescription("description");
		
	}

	// /signup
	@Test
	public void signup_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/signup")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createUserInJson("test","tester"));
		
		when(userService.signup(any())).thenReturn("1234");
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"token\":\"1234\"}"))
	              .andReturn();
	      
	      System.out.println(result.getResponse().getContentAsString());
	}

	//This converts to JSON object
	private static String createUserInJson(String username, String password) {
		return "{ \"username\": \"" + username + "\", " + "\"password\":\"" + password + "\"}";
	}
	
	@Test
	public void login_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson("test", "tester"));
		
		when(userService.login(any())).thenReturn("1234");
		
		MvcResult result = mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().json("{\"token\":\"1234\"}"))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	
	@Test
	public void addPost_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/{username}/post", "someUser")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createUserPostInJson("test","testPost"));
		
		when(userService.addPost((any()), any())).thenReturn(user);
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"username\":\"test\"}"))
	              .andReturn();
	      
	      System.out.println(result.getResponse().getContentAsString());
	} 
	//This converts to JSON object
	private static String createUserPostInJson(String title, String description) {
		return "{ \"title\": \"" + title + "\", " + "\"description\":\"" + description + "\"}";
	}
	

	@Test
	public void addComment_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
			       .post("/user/{username}/{postId}/comment", "someUser", "1")
			       .contentType(MediaType.APPLICATION_JSON)
			       .content(createCommentInJson("text"));
		
		when(userService.addComment((any()),any(),any())).thenReturn(user);
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
//	              .andExpect(content().json("{\"username\":\"test\"}"))
	              .andReturn();
	      
	      System.out.println(result.getResponse().getContentAsString());
	} 
	//This converts to JSON object
	private static String createCommentInJson(String text) {
		return "{ \"text\": \"" + text + "\"}";
	}
	
}

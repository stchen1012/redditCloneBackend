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

import com.ga.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
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
	
//	@Test
//	public void signup_User_Failure() throws Exception {
//		RequestBuilder requestBuilder = MockMvcRequestBuilders
//			       .post("/user/signup")
//			       .contentType(MediaType.APPLICATION_JSON)
//			       .content(createUserInJson("test","tester"));
//		
//		when(userService.signup(any())).thenReturn("");
//		
//		MvcResult result = mockMvc.perform(requestBuilder)
//	              .andExpect(status().isOk())
//	              .andExpect(content().json("{\"token\":\"\"}"))
//	              .andReturn();
//	      
//	      System.out.println(result.getResponse().getContentAsString());
//	}

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
	

	// /{username}/post

	// /{username}/{postId}/comment
}

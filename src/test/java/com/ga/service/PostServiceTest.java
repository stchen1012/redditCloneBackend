package com.ga.service;

import static org.junit.Assert.assertEquals;
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

import com.ga.dao.PostDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class PostServiceTest {

	private List<Post> posts;
	private Post post;
	private List<Comment> comments;
	private Comment comment;

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@InjectMocks
	private User user;
	
	@InjectMocks
	PostServiceImpl postService;
	
	@Mock
	PostDao postDao;
	
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

	}
	
	@Test
	public void getAllPosts_Posts_SUCCESS(){
		when(postDao.getAllPosts()).thenReturn(posts);
		List<Post> allPosts = postService.getAllPosts();
		assertEquals(posts.get(0).getPostId(), allPosts.get(0).getPostId());
	}

	@Test
	public void getAllPostByUsername_Posts_SUCCESS() {
		when(postDao.getAllPostByUsername(anyString())).thenReturn(posts);
		List<Post> postsByUser = postService.getAllPostByUsername("someUser");
		assertEquals(posts.get(0).getPostId(), postsByUser.get(0).getPostId());
	}
	
	@Test
	public void getCommentsByPostId_Comments_SUCCESS() {
		when(postDao.getCommentsByPostId(any())).thenReturn(comments);
		List<Comment> commentsByPostId = postService.getCommentsByPostId(1L);
		assertEquals(comments.get(0).getCommentId(), commentsByPostId.get(0).getCommentId());
	}
	
	@Test
	public void deletePost_PostId_SUCCESS() {
		when(postDao.deletePost(any(),any())).thenReturn(1L);
		Long deletedPost = postService.deletePost("someUser", 1L);
		assertEquals(new Long(1), deletedPost);
	}



}

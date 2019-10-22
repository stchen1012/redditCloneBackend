package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao postDao;
	
	@Override
	public List<Post> getAllPosts() {
		return postDao.getAllPosts();
	}

	@Override
	public List<Post> getAllPostByUserId(Long userId) {
		return postDao.getAllPostByUserId(userId);
	}

	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		return postDao.getCommentsByPostId(postId);
	}

	@Override
	public Post deletePost(Long postId, Long userId) {
		return postDao.deletePost(postId, userId);
	}

}

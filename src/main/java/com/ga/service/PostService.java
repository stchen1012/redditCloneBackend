package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;

public interface PostService {
	public List<Post> getAllPosts();
	public List<Post> getAllPostByUserId(Long userId);
	public List<Comment> getCommentsByPostId(Long postId);
//	public Post deletePost(Long userId, Long postId);
	public Long deletePost(Long postId);
}

package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class PostDaoImpl implements PostDao {
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public List<Post> getAllPosts() {
		List<Post> posts = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			posts = session.createQuery("From Post").getResultList();
		} finally {
			session.close();
		}
		return posts;
	}
	
	@Override
	public List<Post> getAllPostByUserId(Long userId) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			user = session.get(User.class, userId);
		} finally {
			session.close();
		}
		
		return user.getPosts();
	}

	@Override
	public List<Comment> getCommentsByPostId(Long commentId) {
		User user = null;
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			user = session.get(User.class, commentId);
		} finally {
			session.close();
		}
		
		return user.getComments();
	}

	@Override
	public Post deletePost(Long postId, Long userId) {
		Post savedPost = null;
		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			savedPost = session.get(Post.class, postId);
			User user = session.get(User.class, userId);
			
			List<Post> posts = user.getPosts();
			
			posts.remove(savedPost);
			user.setPosts(posts);
			session.saveOrUpdate(user);
			
			session.delete(savedPost);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return savedPost;
	}

}

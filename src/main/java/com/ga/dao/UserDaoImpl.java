package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exceptionhandling.IncorrectLoginException;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public User signup(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			session.save(user);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User login(User user) {
		User savedUser = null;
		
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			
			savedUser = (User)session.createQuery("FROM User u WHERE u.username = '" + 
					user.getUsername() + "'").getSingleResult();
		}
		catch(Exception e) {
			throw new IncorrectLoginException("Incorrect username or password");
		}
		finally {
			session.close();
		}
		return savedUser;
	}
	
	@Override
	public User getUserByUsername(String username) {
		User user = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			user = (User)session.createQuery("FROM User u WHERE u.username = '" + 
				username + "'").uniqueResult();
		} finally {
			session.close();
		}
		
		return user;

}
	
	@Override
	public Post getPostById(long postId) {
		Post post = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			post = session.get(Post.class, postId);
		} finally {
			session.close();
		}
		
		return post;

}


	public User addPost(String username, Post post) {
		User user = getUserByUsername(username);
		Session session = sessionFactory.getCurrentSession();

		if (user == null) {
			return null;
		} 
		
		try {
			
			session.beginTransaction();
			post.setUser(user);	
			session.saveOrUpdate(post);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return user;
	}
		
		

	public User addComment(String username, Long postId, Comment comment) {

		Post post = null;
		User user = getUserByUsername(username);
		post = getPostById(postId);
		if (user == null) {
			return null;
		} 
		Session session = sessionFactory.getCurrentSession();
				try {
			session.beginTransaction();
			comment.setPost(post);
			comment.setUserComment(user);
			session.save(comment);
			
			session.getTransaction().commit();
		} finally {

			session.close();
		}
		return user;
	}
}
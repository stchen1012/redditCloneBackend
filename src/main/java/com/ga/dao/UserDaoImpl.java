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
		} finally {
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

	public User addPost(String username, Post post) {
		User user = getUserByUsername(username);
		Session session = sessionFactory.getCurrentSession();

		if (user == null) {
			return null;
		} 
		
		try {
			
			session.beginTransaction();
			
			List<Post> posts = user.getPosts();
			post.setUser(user);
			posts.add(post);
			user.setPosts(posts);
			
			
			session.update(user);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return user;
	}
		
		

	public User addComment(String username, Comment comment, Post post) {
		Session session = sessionFactory.getCurrentSession();
		
		User user = getUserByUsername(username);
		if (user == null) {
			return null;
		} 
		
		try {
			session.beginTransaction();
			
			List<Comment> comments = user.getComments();
			comments.add(comment);
			user.setComments(comments);
			
			session.save(user);
			
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User addComment(String username, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}
}
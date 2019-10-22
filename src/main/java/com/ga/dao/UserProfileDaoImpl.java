package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;
import com.ga.entity.UserProfile;

@Repository
public class UserProfileDaoImpl implements UserProfileDao {

	@Autowired
	private UserDao userDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserProfile addUserProfile(String username, UserProfile userProfile) {
		User user = userDao.getUserByUsername(username);

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			session.save(userProfile);
			user.setUserProfile(userProfile);
			session.update(user);

			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return userProfile;
	}

	@Override
	public UserProfile getUserProfile(String username) {
		User user = userDao.getUserByUsername(username);
		return user.getUserProfile();
	}

	//TO-DO - Remove old multiple profiles created per user
	@Override
	public UserProfile updateUserProfile(String username, UserProfile updateProfile) {
		User user = userDao.getUserByUsername(username);

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			session.update(updateProfile);
			user.setUserProfile(updateProfile);
			session.update(user);

			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return updateProfile;
	}
}

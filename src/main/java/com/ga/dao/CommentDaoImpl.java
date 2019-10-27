package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.exceptionhandling.DeleteException;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
    public Long deleteComment(String username, Long commentId) throws DeleteException{
        Session session = sessionFactory.getCurrentSession();
        Comment savedComment = null;
        try {
            session.beginTransaction();
            savedComment = session.get(Comment.class, commentId);
            if(!savedComment.getUserComment().getUsername().equals(username)){
            	throw new DeleteException("You are not authorized to delete this comment!");
            }
            session.delete(savedComment);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return savedComment.getCommentId();
    }

	@Override
	public List<Comment> getAllCommentByUserName(String username) {
		User user = null;
		List<Comment> comments = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			user = (User)session.createQuery("FROM User u WHERE u.username = '" + username + "'").uniqueResult();
			Long userId = user.getUserId();
			comments = (List <Comment>)session.createQuery("FROM Comment c WHERE c.userComment =" + userId).getResultList();
		} finally {
			session.close();
		}
		return comments;
	}    
}

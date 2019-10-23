package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
    private SessionFactory sessionFactory;

	@Override
    public Long deleteComment(Long commentId) {
        Session session = sessionFactory.getCurrentSession();
        Comment savedComment = null;
        try {
            session.beginTransaction();
            savedComment = session.get(Comment.class, commentId);
            session.delete(savedComment);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return savedComment.getCommentId();
    }    
}
}

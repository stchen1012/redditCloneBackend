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
public class PostDaoImpl implements PostDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = null;
        
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();
            posts = session.createQuery("From Post ORDER by postId ASC").getResultList();
        } finally {
            session.close();
        }
        return posts;
    }
    
    @Override
    public List<Post> getAllPostByUsername(String username) {
    	List<Post> posts = null;
        User user = null;
        
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();
            user = (User)session.createQuery("FROM User u WHERE u.username = '" + username + "'").getSingleResult();
            Long userId = user.getUserId();
            posts = (List <Post>)session.createQuery("FROM Post p WHERE p.user =" + userId).getResultList();
        } finally {
            session.close();
        }
        
        return posts;
    }
    
    
    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        Post post = null;
        Session session = sessionFactory.getCurrentSession();
        
        try {
            session.beginTransaction();
            post = session.get(Post.class, postId);
            
        } finally {
            session.close();
        }
        
        return post.getComments();
        
    }

    @Override
    public Long deletePost(String username, Long postId) throws DeleteException{
    	System.out.println("RUNS?");
        Session session = sessionFactory.getCurrentSession();
        Post savedPost = null;
        try {
            session.beginTransaction();
            savedPost = session.get(Post.class, postId);
            System.out.println(savedPost.getUser().getUsername() + " SS " + username);
            if(!savedPost.getUser().getUsername().equals(username)){
            	throw new DeleteException("You are not authorized to delete this post!");
            }
            session.delete(savedPost);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return savedPost.getPostId();
    }    
    
}
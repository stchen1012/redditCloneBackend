package com.ga.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ga.dao.PostDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.exceptionhandling.DeleteException;
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostDao postDao;
    
    @Override
    public List<Post> getAllPosts() {
        return postDao.getAllPosts();
    }
    
    @Override
    public List<Post> getAllPostByUsername(String username) {
    	return postDao.getAllPostByUsername(username);
    }
    
    @Override
    public List<Comment> getCommentsByPostId(Long postId) {
        return postDao.getCommentsByPostId(postId);
    }

    @Override
    public Long deletePost(String username, Long postId) throws DeleteException {
        return postDao.deletePost(username, postId);
    }
}
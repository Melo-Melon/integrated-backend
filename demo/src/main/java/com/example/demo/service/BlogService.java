package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Action;
import com.example.demo.model.Blog;


//import org.springframework.web.multipart.MultipartFile;


public interface BlogService {
    
    public Blog createPost(Blog b);

    public Blog updatePost(Blog b);

    public void deletePost(Blog b);
    
    public List<Action> initialize();
    
}

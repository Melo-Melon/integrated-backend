package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.example.demo.model.Action;
import com.example.demo.model.Blog;
import com.example.demo.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;


@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping(value = "blog")
    public ResponseEntity<Map<String, Integer>> createBlog(@RequestBody Blog b){
        blogService.createPost(b);

        Map<String, Integer> map = new HashMap<String,Integer>();
        map.put("check", 1);
 
        return map == null ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(null) 
            : ResponseEntity.ok().body(map);
    }

    @PostMapping(value = "blog/update")
    public Callable<Map<String, Integer>> updateBlog(@RequestBody Blog b){

        Map<String, Integer> map = new HashMap<String,Integer>();

        try{
            blogService.updatePost(b);
            map.put("check", 1);
        } catch (NullPointerException e){
            map.put("check", 0);
        }

        return () -> map;
    }

    @PostMapping(value = "blog/delete")
    public Callable<Map<String, Integer>> deleteBlog(@RequestBody Blog b){

        Map<String, Integer> map = new HashMap<String,Integer>();

        try{
            blogService.deletePost(b);
            map.put("check", 1);
        } catch (NullPointerException e){
            map.put("check", 0);
        }

        return () -> map;
    }

    @PostMapping(value = "blog/view")
    public List<Action> initialize(){
        List<Action> blog = blogService.initialize();
        return blog;
    }




}


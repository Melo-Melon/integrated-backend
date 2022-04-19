package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import com.example.demo.Repository.BlogRepo;
import com.example.demo.model.Action;
import com.example.demo.model.Blog;
import com.example.demo.model.Upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl implements BlogService {


    private BlogRepo blogRepo;

    @Autowired
    public BlogServiceImpl(BlogRepo blogRepo){
        this.blogRepo = blogRepo;
    }
    
    @Override
    public Blog createPost(Blog b) {

        Blog blog = new Blog();
        Upload upload = null;
        List<Upload> uploadArr = new ArrayList<Upload>();

        if (b != null){

            blog.setBlogAuthor(b.getBlogAuthor());
            blog.setBlogAvatar("https://joeschmoe.io/api/v1/random");
            blog.setBlogContent(b.getBlogContent());
            blog.setBlogCreatedOn(LocalDateTime.now());
            blog.setBlogLikes(b.getBlogLikes());
            blog.setBlogDislikes(b.getBlogDislikes());

            if (b.getUpload().isEmpty()){
                upload = null;
            } else {
                for (int i = 0; i < b.getUpload().size(); i++){
                    upload = uploadPic(b, i);
                    upload.setBlog(blog);
                    uploadArr.add(upload);
                }
            }
            blog.setUpload(uploadArr);
        }
        return blogRepo.save(blog);
   
    }

    @Override
    public Blog updatePost(Blog b) {

        Blog blog = blogRepo.findByBlogId(b.getBlogId());
        Upload upload = null;
        List<Upload> uploadArr = new ArrayList<Upload>();

        if (blog == null){
            throw new NullPointerException("No Blog Found");   
        } else if (b.getUpload().isEmpty() || !b.getBlogContent().isEmpty()){
            blog.setBlogContent(b.getBlogContent());
        } 

        if (b.getUpload().isEmpty()){
            upload = null;
        } else {
            for (int i = 0; i < b.getUpload().size(); i++){
                upload = uploadPic(b, i);
                upload.setBlog(blog);
                uploadArr.add(upload);
            }
        }
        
        blog.setUpload(uploadArr);
        blog.setBlogCreatedOn(LocalDateTime.now());

        return blogRepo.save(blog);

    }

    @Override
    public void deletePost(Blog b) {

        Blog blog = blogRepo.findByBlogId(b.getBlogId());
        if (blog == null){
            throw new NullPointerException("No Blog Found");   
        } else {
            blogRepo.delete(blog);
        }
        
        return;

    }

    @Override
    public List<Action> initialize() {

        List<Action> initialized = new ArrayList<Action>();
        List<Blog> blog = blogRepo.findAll();

        for (int i = 0; i < blog.size(); i++){
            initialized.add(init(blog.get(i)));
        }

        return initialized;

    }

    private Action init(Blog blog){

        Action a = new Action();
        Upload upload = new Upload();

        Map<String, Integer> map = new HashMap<String,Integer>();
        List<Object> arr = new ArrayList<Object>();

        a.setBlogId(blog.getBlogId());
        a.setBlogAuthor(blog.getBlogAuthor());
        a.setBlogAvatar(blog.getBlogAvatar());
        a.setBlogContent(blog.getBlogContent());
        a.setBlogCreatedOn(blog.getBlogCreatedOn());

        for (int i = 0; i < blog.getUpload().size(); i++){
            upload = uploadPic(blog, i);
            arr.add(upload);
            a.setUpload(arr);
        }


        map.put("likes", blog.getBlogLikes());
        map.put("dislikes", blog.getBlogDislikes());
        a.setActions(map);

        return a;
    }

    private Upload uploadPic(Blog b, int i){

        Upload upload = new Upload();
        upload.setUid(b.getUpload().get(i).getUid());
        upload.setThumburl(b.getUpload().get(i).getThumburl());
        upload.setUsername(b.getBlogAuthor());
        upload.setName(b.getUpload().get(i).getName());
        upload.setLastModified(b.getUpload().get(i).getLastModified());
        upload.setLastModifiedDate(b.getUpload().get(i).getLastModifiedDate());
        upload.setSize(b.getUpload().get(i).getSize());
        upload.setType(b.getUpload().get(i).getType());
        upload.setError(b.getUpload().get(i).getError());
        upload.setPercent(b.getUpload().get(i).getPercent());
        upload.setResponse(b.getUpload().get(i).getResponse());
        upload.setStatus(b.getUpload().get(i).getStatus());
        upload.setLength(b.getUpload().get(i).getLength());

        return upload;

    }

}
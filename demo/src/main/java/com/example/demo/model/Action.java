package com.example.demo.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Action {

    private int blogId;
    private String blogAuthor;
    private String blogAvatar;
    private String blogContent;
    private LocalDateTime blogCreatedOn;
    private List<Object> upload;

    public int getBlogId() {
        return this.blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogAuthor() {
        return this.blogAuthor;
    }

    public void setBlogAuthor(String blogAuthor) {
        this.blogAuthor = blogAuthor;
    }

    public String getBlogAvatar() {
        return this.blogAvatar;
    }

    public void setBlogAvatar(String blogAvatar) {
        this.blogAvatar = blogAvatar;
    }

    public String getBlogContent() {
        return this.blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public LocalDateTime getBlogCreatedOn() {
        return this.blogCreatedOn;
    }

    public void setBlogCreatedOn(LocalDateTime blogCreatedOn) {
        this.blogCreatedOn = blogCreatedOn;
    }

    private Map<String, Integer> actions;

    public Map<String, Integer> getActions() {
        return this.actions;
    }

    public void setActions(Map<String, Integer> actions) {
        this.actions = actions;
    }

    public List<Object> getUpload() {
        return this.upload;
    }

    public void setUpload(List<Object> upload) {
        this.upload = upload;
    }

   
}

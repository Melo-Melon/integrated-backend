package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "blog")
public class Blog {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int blogId;
    private String blogAuthor;
    private String blogAvatar;
    private String blogContent;
    private LocalDateTime blogCreatedOn;
    private int blogLikes;
    private int blogDislikes;

    @OneToMany(targetEntity = Upload.class, cascade =CascadeType.ALL)
    private List<Upload> upload;

    public List<Upload> getUpload() {
        return this.upload;
    }

    public void setUpload(List<Upload> upload) {
        this.upload = upload;
    }
    
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

    public int getBlogLikes() {
        return this.blogLikes;
    }

    public void setBlogLikes(int blogLikes) {
        this.blogLikes = blogLikes;
    }

    public int getBlogDislikes() {
        return this.blogDislikes;
    }

    public void setBlogDislikes(int blogDislikes) {
        this.blogDislikes = blogDislikes;
    }

    
    

}

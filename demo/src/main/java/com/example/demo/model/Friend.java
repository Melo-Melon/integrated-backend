package com.example.demo.model;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Friend {
    @Id
   private String name;
    private String host;
    private String email;
   
    private String picture_large;
    private String picture_medium;
    private String picture_thumbnail;

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    
    public String getPicture_large() {
        return picture_large;
    }
    public void setPicture_large(String picture_large) {
        this.picture_large = picture_large;
    }
    public String getPicture_medium() {
        return picture_medium;
    }
    public void setPicture_medium(String picture_medium) {
        this.picture_medium = picture_medium;
    }
    public String getPicture_thumbnail() {
        return picture_thumbnail;
    }
    public void setPicture_thumbnail(String picture_thumbnail) {
        this.picture_thumbnail = picture_thumbnail;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
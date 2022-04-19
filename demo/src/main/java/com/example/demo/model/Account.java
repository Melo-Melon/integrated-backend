package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private String user;
    private String pass;

    public Account(){
        
    }

    public Account(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    public String getuser(){
        return user;
    }
    public void setuser(String user){
        this.user=user;
    }
    public String getpass(){
        return pass;
    }
    public void setUserpwd(String pass){
        this.pass=pass;
    }
}

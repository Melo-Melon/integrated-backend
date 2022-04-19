package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Editlist {
    @Id
    private String userid;
    private String edhost;
    public String getEdhost() {
        return edhost;
    }
    public void setEdhost(String edhost) {
        this.edhost = edhost;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
}

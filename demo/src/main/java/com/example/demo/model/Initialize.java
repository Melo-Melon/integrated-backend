package com.example.demo.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Initialize {
    @Id
    private String inhost;

    public String getInhost() {
        return inhost;
    }

    public void setInhost(String inhost) {
        this.inhost = inhost;
    }
}

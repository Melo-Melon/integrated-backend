package com.example.demo.model;

public class EditAccount {
    private String user;
    private String orgpass;
    private String newpass;
    public String getuser() {
        return user;
    }
    public void setuser(String user) {
        this.user = user;
    }
    public String getorgpass() {
        return orgpass;
    }
    public void setUserpwd(String orgpass) {
        this.orgpass = orgpass;
    }
    public String getnewpass() {
        return newpass;
    }
    public void setUsernewpwd(String newpass) {
        this.newpass = newpass;
    }
    
}

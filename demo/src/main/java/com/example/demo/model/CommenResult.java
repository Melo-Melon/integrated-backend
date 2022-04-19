package com.example.demo.model;

//返回码对象的声明
public class CommenResult {
    private Integer check;// 返回码
    public CommenResult(){};
    public CommenResult(Integer check) {
        this.check = check;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }
}

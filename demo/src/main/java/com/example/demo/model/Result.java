package com.example.demo.model;

public class Result {
    private Object data;// 返回数据
    public Result() {
    }

    public Result(Object data) {
        super();
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

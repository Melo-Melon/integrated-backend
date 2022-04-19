package com.example.demo.controller;


import com.example.demo.model.CommenResult;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//Error deal模板，所以异常返回会使用该模板
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public CommenResult exception(Exception e){
        return new CommenResult(0);
    }
}

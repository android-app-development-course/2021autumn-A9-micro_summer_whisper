package com.example.floor_myshop.controller;

import com.example.floor_myshop.model.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResponse error(Exception e){
        e.printStackTrace();
        return ApiResponse.failed();
    }
}

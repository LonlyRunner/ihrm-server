package com.ihrm.ihrm.config;

import com.ihrm.ihrm.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        e.printStackTrace();
        return R.fail("服务器错误：" + e.getMessage());
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public R handleMessageNotReadable(org.springframework.http.converter.HttpMessageNotReadableException e) {
        e.printStackTrace();
        return R.fail("请求参数格式错误：" + e.getMostSpecificCause().getMessage());
    }

    @ExceptionHandler(org.springframework.validation.BindException.class)
    public R handleBindException(org.springframework.validation.BindException e) {
        e.printStackTrace();
        return R.fail("参数绑定失败：" + e.getAllErrors().get(0).getDefaultMessage());
    }
}

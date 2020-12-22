package com.shop.manage.system.support;

import com.alibaba.fastjson.JSON;
import com.shop.manage.system.exception.CustomException;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.joey
 */
@RestControllerAdvice(basePackages = "com.shop.manage.system.controller")
public class ApiResponseBodyException {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Map<String,Object> CustomExceptionHandler(CustomException ex){
        Map<String,Object> map = new HashMap<>(16);
        map.put("code",ex.getCode());
        map.put("msg",ex.getMsg());
        return map;
    }
}

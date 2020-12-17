package com.shop.manage.system.controller;

import com.alibaba.fastjson.JSON;
import com.shop.manage.system.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.joey
 */
@RestController
@RequestMapping("/user")
public class TUserController {

    @Autowired
    TUserService tUserService;

    @GetMapping("/test")
    public Object test(){

        //数据源一信息
        System.out.println(JSON.toJSONString(tUserService.getBaseDbAllUser()));

        //数据源二信息
        System.out.println(JSON.toJSONString(tUserService.getBackDbAllUser()));

        return "";
    }
}

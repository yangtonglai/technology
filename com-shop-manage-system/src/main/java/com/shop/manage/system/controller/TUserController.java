package com.shop.manage.system.controller;

import com.alibaba.fastjson.JSON;
import com.shop.manage.system.entity.TUser;
import com.shop.manage.system.service.TUserService;
import com.shop.manage.system.support.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mr.joey
 */
@RestController
@RequestMapping("/user")
public class TUserController {

    @Autowired
    TUserService tUserService;

    @GetMapping("/test")
    public ServerResponse<List<TUser>> test(){

        //数据源一信息
        System.out.println(JSON.toJSONString(tUserService.getBaseDbAllUser()));

        //数据源二信息
        System.out.println(JSON.toJSONString(tUserService.getBackDbAllUser()));

        return ServerResponse.createBySuccess("查询成功",tUserService.getBaseDbAllUser());
    }
}

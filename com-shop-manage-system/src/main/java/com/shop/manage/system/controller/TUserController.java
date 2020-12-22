package com.shop.manage.system.controller;

import com.alibaba.fastjson.JSON;
import com.shop.manage.system.contant.ProjectContant;
import com.shop.manage.system.entity.TUser;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.service.TUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mr.joey
 */
@RestController
@RequestMapping("/user")
public class TUserController {

    private static final Logger logger = LoggerFactory.getLogger(TUserController.class);

    @Autowired
    TUserService tUserService;

    @GetMapping("/test")
    public List<TUser> test(){

        //数据源一信息
        System.out.println(JSON.toJSONString(tUserService.getBaseDbAllUser()));

        //数据源二信息
        System.out.println(JSON.toJSONString(tUserService.getBackDbAllUser()));

        return tUserService.getBaseDbAllUser();
    }

    @GetMapping("/test1")
    public TUser test1(){

        //数据源一信息
        System.out.println(JSON.toJSONString(tUserService.getBaseDbAllUser()));

        //数据源二信息
        System.out.println(JSON.toJSONString(tUserService.getBackDbAllUser()));

        return tUserService.getBaseDbAllUser().get(0);
    }

    @GetMapping("/test2")
    public TUser test2() throws CustomException {
        //数据源一信息
        System.out.println(JSON.toJSONString(tUserService.getBaseDbAllUser().get(0)));

        //数据源二信息
        System.out.println(JSON.toJSONString(tUserService.getBackDbAllUser().get(0)));

        try {
            int i = 1/0;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            throw new CustomException(ProjectContant.ERROR_500,e.getMessage());
        }


        return tUserService.getBaseDbAllUser().get(0);
    }


    @GetMapping("/test3")
    public List<TUser> test3(@RequestParam("id")String id){

        return tUserService.getBaseDbAllUser();
    }


    @PostMapping("/test4")
    public List<TUser> test4(@RequestBody TUser tUser){

        return tUserService.getBaseDbAllUser();
    }
}

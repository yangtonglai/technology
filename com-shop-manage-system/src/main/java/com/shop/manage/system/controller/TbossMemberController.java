package com.shop.manage.system.controller;

import com.shop.manage.system.business.TboosMemberBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 老板会员中心模块 控制层
 * @author ytl
 * @crate 2020/12/29
 */
@RestController
@RequestMapping("/boss/")
public class TbossMemberController {
    @Autowired
    private TboosMemberBusiness tboosMemberBusiness;

    /**
     * 板会员中心模块 会员中心添加接口
     * @return
     */
    @PostMapping("addMember")
    public void addMember(){
        tboosMemberBusiness.addMember();
    }

}

package com.shop.manage.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.manage.system.entity.TUser;

import java.util.List;

/**
 * @author Mr.joey
 */
public interface TUserService extends IService<TUser> {


    public List<TUser> getBaseDbAllUser();

    public List<TUser> getBackDbAllUser();
}

package com.shop.manage.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.manage.system.annotation.DataSource;
import com.shop.manage.system.contant.DataSourceConstants;
import com.shop.manage.system.entity.TUser;
import com.shop.manage.system.mapper.TUserMapper;
import com.shop.manage.system.service.TUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.joey
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper,TUser> implements TUserService {


    @Override
    public List<TUser> getBaseDbAllUser() {
        return baseMapper.selectList(null);
    }

    @DataSource(DataSourceConstants.BACK_DB)
    @Override
    public List<TUser> getBackDbAllUser() {
        return baseMapper.selectList(null);
    }
}

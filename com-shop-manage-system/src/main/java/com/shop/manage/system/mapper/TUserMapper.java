package com.shop.manage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.manage.system.entity.TUser;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Mr.joey
 */
public interface TUserMapper extends BaseMapper<TUser> {
     TUser saveUser(@RequestBody TUser tUser);
}

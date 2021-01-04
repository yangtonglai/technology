package com.shop.manage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.manage.system.entity.TUser;
import com.shop.manage.system.vo.MemberResVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mr.joey
 */
public interface TUserMapper extends BaseMapper<TUser> {
     MemberResVo getMember(@RequestParam(value = "id")Integer id);
     Integer saveMember(TUser tUser);
}

package com.shop.manage.system.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ytl
 * @crate 2020/12/31
 * 账号注册 vo类
 */
@Data
public class UserInfoAddVo {
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称（会员名称）
     */
    @NotNull(message = "会员名称不能为空！")
    private String nickName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 真实名称
     */
    private String factName;

    /**
     * 会员等级
     */
    private Integer memberLevel;
    /**
     * 账号角色
     */
    @NotNull(message = "账号角色不能为空！")
    private Integer roleId;
    /**
     * 职务
     */
    @NotNull(message = "账号职务不能为空")
    private Integer orgId;

    /**
     * 头像地址
     */
    private String headImageUrl;
}

package com.shop.manage.system.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ytl
 * @crate 2021/1/4
 * 会员中心账号查询vo
 */
@Data
public class MemberResVo {
    /**
     * 账号id
     */
    private Integer id;
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称（会员名称）
     */
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
     * 账号角色id
     */
    private Integer roleId;
    /**
     * 职务id
     */
    private Integer orgId;
    /**
     * 门店id
     */
    private Integer shopId;

    /**
     * 账号角色
     */
    private String roleName;
    /**
     * 职务
     */
    private String orgName;

    /**
     * 头像地址
     */
    private String headImageUrl;
    /**
     * 地址
     */
    private String userAddress;
}

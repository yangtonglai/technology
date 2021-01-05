package com.shop.manage.system.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ytl
 * @crate 2020/12/31
 * 账号信息修改 vo类
 */
@Data
public class MemberUpdateVo {

    /**
     * 接口类型 传00：角色状态下架，传01：角色信息修改
     */
    @NotNull(message = "接口类型参数不能为空")
    private String type;
    /**
     * 会员账号id
     */
    @NotNull(message = "会员ID不能为空")
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
     * 头像地址
     */
    private String headImageUrl;
    /**
     * 地址
     */
    private String userAddress;
}

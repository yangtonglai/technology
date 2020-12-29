package com.shop.manage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  会员信息表
 * </p>
 *
 * @author yangtl
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 启用状态 0：未启用 1：已启用
     */
    private String isAvailable;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyUser;

    /**
     * 会员等级
     */
    private Integer memberLevel;

    /**
     * 头像地址
     */
    private String headImageUrl;


}

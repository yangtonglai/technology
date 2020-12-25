package com.shop.manage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *  导航菜单栏表
 * </p>
 *
 * @author yangtl
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TNavigate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单地址
     */
    private String menuUrl;

    /**
     * 如果根结点为 -1
     */
    private Integer parentMenuId;

    /**
     * 是否子菜单  0：否1：是
     */
    private String isChildrenMenu;

    /**
     * 启用状态 0:未启用  1：启用
     */
    private String isAvailable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人
     */
    private String modifyUser;


}

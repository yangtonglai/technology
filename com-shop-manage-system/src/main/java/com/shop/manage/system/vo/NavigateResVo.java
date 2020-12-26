package com.shop.manage.system.vo;

import lombok.Data;
import com.shop.manage.system.entity.TNavigate;

import java.util.List;

/**
 * 底部菜单栏查询vo
 * @author ytl
 */
@Data
public class NavigateResVo {
    /**
     * 菜单id
     */
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
     * 子菜单
     */
    private List<TNavigate> childList;
}

package com.shop.manage.system.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 底部菜单栏添加vo
 * @author YY
 */
@Data
public class NavigateAddVo {
    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空")
    private String menuName;
    /**
     * 菜单地址
     */
    private String menuUrl;
    /**
     * 菜单id如果不是-1 则为子菜单
     */
    private Integer menuId;

}

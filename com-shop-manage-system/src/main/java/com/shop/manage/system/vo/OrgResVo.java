package com.shop.manage.system.vo;

import lombok.Data;

/**
 * @author ytl
 * @crate 2020/12/29
 * 职务信息
 */
@Data
public class OrgResVo {
    /**
     * 职务id
     */
    private Integer orgId;

    /**
     * 职务名称
     */
    private String orgName;

    /**
     * 店门id
     */
    private Integer shopId;
    /**
     * 店门名称
     */
    private Integer shopName;
}

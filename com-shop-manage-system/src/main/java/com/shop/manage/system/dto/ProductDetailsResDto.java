package com.shop.manage.system.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ytl
 * @version 1.0
 * @date 2020/12/26
 *
 * 产品详情返回 dto
 */
@Data
public class ProductDetailsResDto {
    /**
     * 产品Id
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品类型id
     */
    private Integer productTypeId;

    /**
     * 产品规格
     */
    private String productStandard;

    /**
     * 产品产地
     */
    private String producingArea;

    /**
     * 启用状态 0:未启用 1：已启用
     */
    private String isAvailable;
    /**
     * 价格
     */
    private String price;
    /**
     * 图片url
     */
    private String productImageUrl;
}

package com.shop.manage.system.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author ytl
 * @crate 2021/1/5
 * 订单分类dto
 */
@Data
public class OrderSortResDto {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 产品名称
     */
    private String shopProductName;

    /**
     * 产品类型名称
     */
    private String productTypeName;

    /**
     * 产品规格
     */
    private String productStandard;

    /**
     * 产品产地
     */
    private String producingArea;

    /**
     *  0:待处理 1：已发货 2：已收货 3：已入库 4：已收款 5 ：异常订单
     */
    private Integer status;

    /**
     * 产品价格
     */
    private String price;

    /**
     * 采购量
     */
    private String buyCount;
    /**
     * 订单图片
     */
    private String productImageUrl;

}

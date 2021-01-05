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
 *  订单图片表
 * </p>
 *
 * @author yangtl
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TShopOrderFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 订单id
     */
    private Integer shopOrderId;

    /**
     * 图片url
     */
    private String productImageUrl;

    /**
     * 启用状态 0：未  1：启
     */
    private String isAvailable;

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
     * 订单状态 0:待处理 1：已发货 2：已收货 3：已入库 4：已收款 5 ：异常订单
     */
    private Integer status;


}

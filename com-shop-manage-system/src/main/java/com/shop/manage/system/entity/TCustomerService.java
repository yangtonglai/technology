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
 *  客服信息表
 * </p>
 *
 * @author yangtl
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TCustomerService implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客服联系方式
     */
    private String contactInfo;

    /**
     * 客服名称
     */
    private String contactName;

    /**
     * 启用状态 0:未启用  1：启用
     */
    private String isAvailable;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * create_user
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


}

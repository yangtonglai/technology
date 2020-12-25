package com.shop.manage.system.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 页首平台形象展示 查询接口返回数据vo
 */
@Data
public class IndexAddVo {
    /**
     * 图片名称
     */
    private String fileName;
    /**
     * 图片地址
     */
    @NotNull(message = "图片地址不能为空")
    private String fileUrl;
    /**
     * 描述介绍
     */
    private String contentInfo;

}

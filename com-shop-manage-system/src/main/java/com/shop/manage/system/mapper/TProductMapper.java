package com.shop.manage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.manage.system.dto.ProductDetailsResDto;
import com.shop.manage.system.entity.TProduct;
import com.shop.manage.system.vo.ProductDetailsResVo;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangtonglai
 * @since 2020-12-26
 */
public interface TProductMapper extends BaseMapper<TProduct> {
       List<ProductDetailsResDto>getProductDetail();
}

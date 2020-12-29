package com.shop.manage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.manage.system.dto.ProductDetailsResDto;
import com.shop.manage.system.entity.TProduct;
import com.shop.manage.system.vo.ProductDetailsResVo;
import org.springframework.web.bind.annotation.RequestParam;

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
       /**
        * 查询产品详情
        * @return
        */
       List<ProductDetailsResDto>getProductDetail();
       /**
        * 查询产品供货价格详情
        * @return
        */
       List<ProductDetailsResDto>getMemberPrice(@RequestParam(value = "member_level")Integer member_level);

}

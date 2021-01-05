package com.shop.manage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.manage.system.dto.OrderSortResDto;
import com.shop.manage.system.entity.TShopOrder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangtl
 * @since 2021-01-05
 */
public interface TShopOrderMapper extends BaseMapper<TShopOrder> {
    List<OrderSortResDto>getProductSort();
}

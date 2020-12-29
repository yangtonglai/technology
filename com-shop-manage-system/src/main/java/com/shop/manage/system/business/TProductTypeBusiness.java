package com.shop.manage.system.business;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shop.manage.system.contant.ProjectContant;
import com.shop.manage.system.contant.commonContants;
import com.shop.manage.system.dto.ProductDetailsResDto;
import com.shop.manage.system.entity.TIndex;
import com.shop.manage.system.entity.TProduct;
import com.shop.manage.system.entity.TProductType;
import com.shop.manage.system.entity.TUser;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.mapper.TProductMapper;
import com.shop.manage.system.service.TProductFileService;
import com.shop.manage.system.service.TProductService;
import com.shop.manage.system.service.TProductTypeService;
import com.shop.manage.system.service.TUserService;
import com.shop.manage.system.vo.ProductDetailsResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  行情模块 业务层
 * </p>
 *
 * @author yangtl
 * @since 2020-12-23
 */
@Component
@Slf4j
public class TProductTypeBusiness {

    @Autowired
    private TProductTypeService tProductTypeService;
    @Autowired
    private TProductFileService tProductFileService;
    @Autowired
    private TProductService tProductService;
    @Autowired
    private TProductMapper tProductMapper;
    @Autowired
    private TUserService tUserService;

    /**
     * 产品类型 查询
     * @return
     */
    public List<TProductType> getProductType() {
       return  tProductTypeService.list(new LambdaQueryWrapper<TProductType>()
                .eq(TProductType::getIsAvailable,commonContants.IS_AVAILABLE));
    }

    /**
     * 产品类型 添加
     * @param productTypeName
     */
    public void addProductType(String productTypeName) {
        TProductType data = new TProductType();
        Date date = new Date();
        data.setIsAvailable(commonContants.IS_AVAILABLE);
        data.setProductTypeName(productTypeName);
        data.setCreateTime(date);
        data.setCreateUser("yy");
        data.setModifyTime(date);
        data.setModifyUser("yy");
        tProductTypeService.save(data);
    }

    /**
     * 产品类型 更新
     *  同时更新产品表产品上下架
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateProductType(Integer id) throws CustomException {
        //产品类型上下架
        try {
            TProductType updateData = new TProductType();
            updateData.setId(id);
            updateData.setIsAvailable(commonContants.NOT_AVAILABLE);
            updateData.setModifyUser("yy");
            updateData.setModifyTime(new Date());
            tProductTypeService.updateById(updateData);
            //同步产品上下架
            List<TProduct> products = tProductService.list(new LambdaQueryWrapper<TProduct>()
                    .eq(TProduct::getProductTypeId, id)
                    .eq(TProduct::getIsAvailable, commonContants.IS_AVAILABLE));
            if (BeanUtil.isNotEmpty(products)){
                Set<Integer> productIds = products.stream().map(TProduct::getId).collect(Collectors.toSet());
                List<TProduct> tProducts = new ArrayList<>();
                for (Integer productId : productIds) {
                    TProduct vo = new TProduct();
                    vo.setId(productId);
                    vo.setIsAvailable(commonContants.NOT_AVAILABLE);
                    vo.setModifyTime(new Date());
                    vo.setModifyUser("yy");
                    tProducts.add(vo);
                }
                tProductService.updateBatchById(products);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomException(ProjectContant.ERROR_500,e.getMessage());
        }

    }

    /**
     * 产品详情查询
     * @return
     */
    public List<ProductDetailsResVo> getProductDetail() {
        //查询产品类型和图片
        List<ProductDetailsResVo> data = new ArrayList<>();
        List<ProductDetailsResDto> productList = tProductMapper.getProductDetail();
        if (BeanUtil.isEmpty(productList)){
            return data;
        }
        //根据产品id、分组
        Map<Integer, List<ProductDetailsResDto>> productMap = productList.stream().collect(Collectors.groupingBy(ProductDetailsResDto::getId));
        //获取产品的图片list
        Map<Integer,List<String>> imageMap = new HashMap<>();
        for (ProductDetailsResDto resVo : productList) {
            List<ProductDetailsResDto> productDetailsResVos = productMap.get(resVo.getId());
            List<String> collect = productDetailsResVos.stream().map(ProductDetailsResDto::getProductImageUrl).collect(Collectors.toList());
            imageMap.put(resVo.getId(),collect);
        }
        //数据封装
        for (ProductDetailsResDto resDto : productList) {
            ProductDetailsResVo vo = new ProductDetailsResVo();
            if (MapUtil.isNotEmpty(imageMap)&&BeanUtil.isNotEmpty(imageMap.get(resDto.getId()))){
                vo.setProductImageList(imageMap.get(resDto.getId()));
            }
            BeanUtil.copyProperties(resDto,vo);
            data.add(vo);
        }
        return data;
    }

    /**
     * 会员等级价格
     * @param userId
     * @return
     */
    public List<ProductDetailsResVo> getMemberPrice(String userId) {
        List<ProductDetailsResVo> data = new ArrayList<>();
        //查询该账号的会员等级
        TUser tUser = tUserService.getOne(new LambdaQueryWrapper<TUser>()
                .select(TUser::getId,TUser::getMemberLevel)
                .eq(TUser::getId, userId)
                .eq(TUser::getIsAvailable, commonContants.IS_AVAILABLE));
        if (BeanUtil.isEmpty(tUser)){
            return data;
        }
        List<ProductDetailsResDto> memberPrice = tProductMapper.getMemberPrice(tUser.getMemberLevel());
       //根据产品id、分组
        Map<Integer, List<ProductDetailsResDto>> productMap = memberPrice.stream().collect(Collectors.groupingBy(ProductDetailsResDto::getId));
        //获取产品的图片list
        Map<Integer,List<String>> imageMap = new HashMap<>();
        for (ProductDetailsResDto resVo : memberPrice) {
            List<ProductDetailsResDto> productDetailsResVos = productMap.get(resVo.getId());
            List<String> collect = productDetailsResVos.stream().map(ProductDetailsResDto::getProductImageUrl).collect(Collectors.toList());
            imageMap.put(resVo.getId(),collect);
        }
        //数据封装
        for (ProductDetailsResDto resDto : memberPrice) {
            ProductDetailsResVo vo = new ProductDetailsResVo();
            if (MapUtil.isNotEmpty(imageMap)&&BeanUtil.isNotEmpty(imageMap.get(resDto.getId()))){
                vo.setProductImageList(imageMap.get(resDto.getId()));
            }
            BeanUtil.copyProperties(resDto,vo);
            data.add(vo);
        }
        return data;
    }
}

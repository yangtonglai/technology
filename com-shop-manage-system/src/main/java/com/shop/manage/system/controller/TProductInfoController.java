package com.shop.manage.system.controller;


import com.shop.manage.system.business.TProductInfoBusiness;
import com.shop.manage.system.entity.TProductType;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.vo.ProductDetailsResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  行情模块 业务层
 * </p>
 *
 * @author yangtonglai
 * @since 2020-12-26
 */
@RestController
@RequestMapping("/market/")
public class TProductInfoController {

    @Autowired
    private TProductInfoBusiness tProductInfoBusiness;


    /**
     * 产品分类模块
     * 产品类型  查询接口
     * @return
     */
    @GetMapping("getProductType")
    public List<TProductType> getProductType(){
        return tProductInfoBusiness.getProductType();
    }

    /**
     * 产品分类模块
     * 产品类型  添加接口
     * @return
     */
    @GetMapping("addProductType")
    public void addProductType(@RequestParam(value = "productTypeName") String productTypeName){
        tProductInfoBusiness.addProductType(productTypeName);
    }

    /**
     * 产品分类模块
     * 产品类型  更新接口
     * @return
     */
    @GetMapping("updateProductType")
    public void updateProductType(@RequestParam(value = "id")Integer id) throws CustomException {
        tProductInfoBusiness.updateProductType(id);
    }


    /**
     * 产品详情模块
     * 产品详情  查询接口
     * @return
     */
    @GetMapping("getProductDetail")
    public List<ProductDetailsResVo> getProductDetail(){
        return tProductInfoBusiness.getProductDetail();
    }

    /**
     * 产品详情模块
     * 会员等级价格  查询接口
     * @return
     */
    // TODO: 2020/12/29 用户id
    @GetMapping("getMemberPrice")
    public List<ProductDetailsResVo> getMemberPrice(String userId){
        return tProductInfoBusiness.getMemberPrice(userId);
    }



}

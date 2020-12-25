package com.shop.manage.system.controller;


import com.shop.manage.system.business.TIndexBusiness;
import com.shop.manage.system.entity.TBanner;
import com.shop.manage.system.entity.TCustomerService;
import com.shop.manage.system.entity.TIndex;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.vo.IndexAddVo;
import com.shop.manage.system.vo.NavigateAddVo;
import com.shop.manage.system.vo.NavigateQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  首页行情控制层
 * </p>
 *
 * @author yangtl
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/tIndex")
@Slf4j
public class TIndexController {

    @Autowired
    private TIndexBusiness tIndexBusiness;

    /**
     * 页首平台形象展示 查询接口
     * @return
     */
    @GetMapping("getIndexInfo")
    public List<TIndex> getIndexInfo(){
        return tIndexBusiness.getIndexInfo();
    }

    /**
     * 页首平台形象展示 添加接口
     * @return
     */
    @PostMapping("addIndexInfo")
    public void addIndexInfo(@RequestBody @Valid IndexAddVo indexAddVo){
         tIndexBusiness.addIndexInfo(indexAddVo);
    }

    /**
     * 页首平台形象展示 更新接口
     * @return
     */
    @GetMapping("updateIndexInfo")
    public void updateIndexInfo(@RequestParam(value = "id" ) Integer id){
        tIndexBusiness.updateIndexInfo(id);
    }


    /**
     * 底部菜单导航栏 查询接口
     * @return
     */
    @GetMapping("getNavigateInfo")
    public List<NavigateQueryVo> getNavigateInfo(){
        return tIndexBusiness.getNavigateInfo();
    }

    /**
     * 底部菜单导航栏 添加接口
     * @return
     */
    @PostMapping("addNavigateInfo")
    public void addNavigateInfo(@RequestBody @Valid NavigateAddVo navigateAddVo){
        tIndexBusiness.addNavigateInfo(navigateAddVo);
    }

    /**
     * 底部菜单导航栏 更新接口
     * @return
     */
    @GetMapping("updateNavigateInfo")
    public void updateNavigateInfo(@RequestParam(value = "id" ,required = true) Integer id) throws CustomException {
        tIndexBusiness.updateNavigateInfo(id);
    }


    /**
     * 广告轮播图 查询接口
     * @return
     */
    @GetMapping("getBannerInfo")
    public List<TBanner> getBannerInfo(){
        return tIndexBusiness.getBannerInfo();
    }

    /**
     * 广告轮播图 添加接口
     * @return
     */
    @GetMapping("addBannerInfo")
    public void addBannerInfo(@RequestParam(value = "fileName") String fileName,
                              @RequestParam(value = "fileUrl") String fileUrl){
        tIndexBusiness.addBannerInfo(fileName,fileUrl);
    }

    /**
     * 广告轮播图 更新接口
     * @return
     */
    @GetMapping("updateBannerInfo")
    public void updateBannerInfo(@RequestParam(value = "id" ,required = true) Integer id){
        tIndexBusiness.updateBannerInfo(id);
    }



    /**
     * 客服信息 查询接口
     * @return
     */
    @GetMapping("getCustomerInfo")
    public List<TCustomerService> getCustomerInfo(){
        return tIndexBusiness.getCustomerInfo();
    }

    /**
     * 客服信息 添加接口
     * @return
     */
    @GetMapping("addCustomerInfo")
    public void addCustomerInfo(@RequestParam(value = "contactInfo") String contactInfo,
                              @RequestParam(value = "contactName") String contactName){
        tIndexBusiness.addCustomerInfo(contactInfo,contactName);
    }

    /**
     * 客服信息 更新接口
     * @return
     */
    @GetMapping("updateCustomerInfo")
    public void updateCustomerInfo(@RequestParam(value = "id" ,required = true) Integer id){
        tIndexBusiness.updateCustomerInfo(id);
    }


}


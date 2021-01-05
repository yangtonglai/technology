package com.shop.manage.system.business;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shop.manage.system.contant.IndexContants;
import com.shop.manage.system.contant.ProjectContant;
import com.shop.manage.system.contant.commonContants;
import com.shop.manage.system.entity.TBanner;
import com.shop.manage.system.entity.TCustomerService;
import com.shop.manage.system.entity.TIndex;
import com.shop.manage.system.entity.TNavigate;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.service.TBannerService;
import com.shop.manage.system.service.TCustomerServiceService;
import com.shop.manage.system.service.TIndexService;
import com.shop.manage.system.service.TNavigateService;
import com.shop.manage.system.vo.IndexAddVo;
import com.shop.manage.system.vo.NavigateAddVo;
import com.shop.manage.system.vo.NavigateResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  首页行情业务层
 * </p>
 *
 * @author yangtl
 * @since 2020-12-23
 */
@Component
@Slf4j
public class TIndexBusiness {

    @Autowired
    private TIndexService tIndexService;
    @Autowired
    private TBannerService tBannerService;
    @Autowired
    private TNavigateService tNavigateService;
    @Autowired
    private TCustomerServiceService tCustomerServiceService;

    /**
     * 页首平台形象展示 查询接口
     * @return
     */
    public List<TIndex> getIndexInfo() {
        return tIndexService.list(new LambdaQueryWrapper<TIndex>()
                .select(TIndex::getId, TIndex::getFileName, TIndex::getContentInfo, TIndex::getFileUrl)
                .eq(TIndex::getIsAvailable, commonContants.IS_AVAILABLE));
    }

    /**
     * 页首平台形象展示 添加接口
     */
    public void addIndexInfo(@RequestBody IndexAddVo indexAddVo) {
        Date date = new Date();
        TIndex data = new TIndex();
        data.setFileName(indexAddVo.getFileName());
        data.setFileUrl(indexAddVo.getFileUrl());
        data.setContentInfo(indexAddVo.getContentInfo());
        data.setIsAvailable(commonContants.IS_AVAILABLE);
        data.setCreateTime(date);
        data.setCreateUser("yy");
        data.setModifyTime(date);
        data.setModifyUser("yy");
        tIndexService.save(data);

    }

    /**
     * 页首平台形象展示 更新接口
     * @param id
     */
    public void updateIndexInfo(Integer id) {
        TIndex updateData = new TIndex();
        updateData.setId(id);
        updateData.setIsAvailable(commonContants.NOT_AVAILABLE);
        updateData.setModifyUser("yy");
        updateData.setModifyTime(new Date());
        tIndexService.updateById(updateData);
    }

    /**
     * 底部菜单导航栏 查询接口
     * @return
     */
    public List<NavigateResVo> getNavigateInfo() {
        List<NavigateResVo> data = new ArrayList<>();
        //查询所有启用状态且为父节点菜单
        List<TNavigate> parentList = tNavigateService.list(
                new LambdaQueryWrapper<TNavigate>()
                        .eq(TNavigate::getIsAvailable, commonContants.IS_AVAILABLE)
        );
        if (parentList.isEmpty()||parentList.size()==0){
            return data;
        }
        //查询字节点菜单
        List<TNavigate> list = tNavigateService.list(
                new LambdaQueryWrapper<TNavigate>()
                        .eq(TNavigate::getIsAvailable, commonContants.IS_AVAILABLE)
                        .gt(TNavigate::getParentMenuId, IndexContants.IS_PARENT_MENU)
                        .eq(TNavigate::getIsChildrenMenu,IndexContants.IS_CHILDREN_MENU)
        );
        //数据封装
        for (TNavigate tNavigate : parentList) {
            if (CollUtil.isNotEmpty(list)){
                Map<Integer, List<TNavigate>> collect = list.stream().collect(Collectors.groupingBy(TNavigate::getParentMenuId));
                if (tNavigate.getParentMenuId()==-1) {
                    NavigateResVo vo = new NavigateResVo();
                    BeanUtil.copyProperties(tNavigate, vo);
                    List<TNavigate> tNavigates = collect.get(tNavigate.getId());
                    if (CollUtil.isNotEmpty(tNavigates)) {
                        vo.setChildList(tNavigates);
                    }
                    data.add(vo);
                }
            }
        }
        return data;
    }

    /**
     * 涉及根菜单添加和子菜单添加
     *  1、parentMenuId不传或者为空则为父节点
     *  2、parentMenuId不为-1和和空则为子节点
     * @param navigateAddVo
     */
    public void addNavigateInfo( NavigateAddVo navigateAddVo) {
        //父节点插入
        if (BeanUtil.isEmpty(navigateAddVo.getMenuId())||navigateAddVo.getMenuId()==-1){
            TNavigate tNavigate = new TNavigate();
            Date date = new Date();
            tNavigate.setMenuName(navigateAddVo.getMenuName());
            tNavigate.setMenuUrl(navigateAddVo.getMenuUrl());
            tNavigate.setParentMenuId(IndexContants.IS_PARENT_MENU);
            tNavigate.setIsChildrenMenu(IndexContants.NOT_CHILDREN_MENU);
            tNavigate.setIsAvailable(commonContants.IS_AVAILABLE);
            tNavigate.setCreateTime(date);
            tNavigate.setRoleId(123);
            tNavigate.setModifyUser("yy");
            tNavigate.setModifyTime(date);
            tNavigateService.save(tNavigate); }
        //子节点插入
        else {
            TNavigate tNavigate = new TNavigate();
            Date date = new Date();
            tNavigate.setMenuName(navigateAddVo.getMenuName());
            tNavigate.setMenuUrl(navigateAddVo.getMenuUrl());
            tNavigate.setParentMenuId(navigateAddVo.getMenuId());
            tNavigate.setIsChildrenMenu(IndexContants.IS_CHILDREN_MENU);
            tNavigate.setIsAvailable(commonContants.IS_AVAILABLE);
            tNavigate.setCreateTime(date);
            tNavigate.setRoleId(123);
            tNavigate.setModifyUser("yy");
            tNavigate.setModifyTime(date);
            tNavigateService.save(tNavigate);
        }

    }


    /**
     * 底部菜单导航栏 更新接口
     *  1、更新父节点菜单，子节点一起失效
     *  2、跟新子节点菜单
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateNavigateInfo(Integer id) throws CustomException {
        //查询id是什么节点。
        TNavigate one = tNavigateService.getOne(new LambdaQueryWrapper<TNavigate>()
                .eq(TNavigate::getId, id)
                .eq(TNavigate::getIsAvailable,commonContants.IS_AVAILABLE));
        if (BeanUtil.isEmpty(one)){
            throw new CustomException(ProjectContant.ERROR_500,"没有这个菜单选项！");
        }
        //父节点菜单
        if (!BeanUtil.isEmpty(one.getParentMenuId())&&
                IndexContants.IS_PARENT_MENU == one.getParentMenuId()){
            //查询有无子菜单
            List<TNavigate> list = tNavigateService.list(new LambdaQueryWrapper<TNavigate>().eq(TNavigate::getParentMenuId, one.getId()));
            //提取子菜单id
            List<TNavigate> menuList = new ArrayList<>();
            if (CollUtil.isNotEmpty(list)){
                for (TNavigate tNavigate : list) {
                    TNavigate vo = new TNavigate();
                    vo.setId(tNavigate.getId());
                    vo.setIsAvailable(commonContants.NOT_AVAILABLE);
                    vo.setModifyTime(new Date());
                    vo.setModifyUser("yy");
                    menuList.add(vo);
                }
            }
            try {
                //父菜单失效
                TNavigate vo = new TNavigate();
                vo.setId(one.getId());
                vo.setIsAvailable(commonContants.NOT_AVAILABLE);
                vo.setModifyTime(new Date());
                vo.setModifyUser("yy");
                tNavigateService.updateById(vo);
                //子菜单失效
                tNavigateService.updateBatchById(menuList);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                throw new CustomException(ProjectContant.ERROR_500,e.getMessage());
            }
        }
        //子节点单个失效
        else {
            TNavigate vo = new TNavigate();
            vo.setId(one.getId());
            vo.setIsAvailable(commonContants.NOT_AVAILABLE);
            vo.setModifyTime(new Date());
            vo.setModifyUser("yy");
            tNavigateService.updateById(vo);
        }

    }

    /**
     * 广告轮播图 查询接口
     * @return
     */
    public List<TBanner> getBannerInfo() {
        return tBannerService.list(new LambdaQueryWrapper<TBanner>()
                .select(TBanner::getFileName,TBanner::getFileUrl,TBanner::getId)
                .eq(TBanner::getIsAvailable,commonContants.IS_AVAILABLE));
    }

    /**
     * 广告轮播图 添加接口
     * @param fileName
     * @param fileUrl
     */
    public void addBannerInfo(String fileName, String fileUrl) {
        Date date = new Date();
        TBanner tBanner = new TBanner();
        tBanner.setFileName(fileName);
        tBanner.setFileUrl(fileUrl);
        tBanner.setCreateTime(date);
        tBanner.setIsAvailable(commonContants.IS_AVAILABLE);
        tBanner.setCreateUser("yy");
        tBanner.setModifyUser("yy");
        tBanner.setModifyTime(date);
        tBannerService.save(tBanner);
    }

    /**
     * 广告轮播图 更新接口
     * @param id
     */
    public void updateBannerInfo(Integer id) {
        TBanner tBanner = new TBanner();
        tBanner.setId(id);
        tBanner.setIsAvailable(commonContants.NOT_AVAILABLE);
        tBanner.setModifyTime(new Date());
        tBanner.setModifyUser("yy");
        tBannerService.updateById(tBanner);
    }

    /**
     * 客服信息 查询
     * @return
     */
    public List<TCustomerService> getCustomerInfo() {
        return tCustomerServiceService.list(new LambdaQueryWrapper<TCustomerService>()
                .eq(TCustomerService::getIsAvailable, commonContants.IS_AVAILABLE));
    }

    /**
     * 客服信息新增
     * @param contactInfo
     * @param contactName
     */
    public void addCustomerInfo(String contactInfo, String contactName) {
        TCustomerService tCustomerService = new TCustomerService();
        Date date = new Date();
        tCustomerService.setContactInfo(contactInfo);
        tCustomerService.setContactName(contactName);
        tCustomerService.setIsAvailable(commonContants.IS_AVAILABLE);
        tCustomerService.setCreateTime(date);
        tCustomerService.setCreateUser("yy");
        tCustomerService.setModifyTime(date);
        tCustomerService.setModifyUser("yy");
        tCustomerServiceService.save(tCustomerService);

    }

    /**
     * 客服信息 更新
     * @param id
     */
    public void updateCustomerInfo(Integer id) {
        TCustomerService tCustomerService = new TCustomerService();
        tCustomerService.setId(id);
        tCustomerService.setIsAvailable(commonContants.NOT_AVAILABLE);
        tCustomerService.setModifyTime(new Date());
        tCustomerService.setModifyUser("yy");
        tCustomerServiceService.updateById(tCustomerService);
    }
}


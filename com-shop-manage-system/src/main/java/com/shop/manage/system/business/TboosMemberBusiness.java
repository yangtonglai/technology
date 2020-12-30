package com.shop.manage.system.business;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shop.manage.system.contant.ProjectContant;
import com.shop.manage.system.contant.commonContants;
import com.shop.manage.system.entity.*;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.mapper.TOrgMapper;
import com.shop.manage.system.service.TOrgService;
import com.shop.manage.system.service.TRoleService;
import com.shop.manage.system.service.TShopOrgService;
import com.shop.manage.system.service.TShopService;
import com.shop.manage.system.vo.OrgResVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ytl
 * @crate 2020/12/29
 * 老板会员中心业务层
 */
@Component
@Slf4j
public class TboosMemberBusiness {

    @Autowired
    private TShopService tShopService;
    @Autowired
    private TOrgService tOrgService;
    @Autowired
    private TShopOrgService tShopOrgService;
    @Autowired
    private TOrgMapper tOrgMapper;
    @Autowired
    private TRoleService tRoleService;

    public void addMember() {
    }


    /**
     * 门店添加
     * @param shopName
     */
    public void addShopInfo(String shopName) {
        TShop addVo = new TShop();
        Date date = new Date();
        addVo.setShopName(shopName);
        addVo.setIsAvailable(commonContants.IS_AVAILABLE);
        addVo.setCreateTime(date);
        addVo.setCreateUser("yy");
        addVo.setModifyTime(date);
        addVo.setModifyUser("yy");
        tShopService.save(addVo);
    }

    /**
     * 门店信息查询
     * @return
     */
    public List<TShop> getShopInfo() {
        return tShopService.list(new LambdaQueryWrapper<TShop>()
                .select(TShop::getId,TShop::getIsAvailable,TShop::getShopName,TShop::getShopParentId)
                .eq(TShop::getIsAvailable,commonContants.IS_AVAILABLE));
    }

    /**
     * 门店信息更新
     * @param type
     * @param id
     * @param shopName
     * @param shopParentId
     */
    public void updateShopInfo(String type,Integer id, String shopName, Integer shopParentId) {
        //门店下架
        if (commonContants.IS_DELETE.equals(type)){
            TShop deleteVo = new TShop();
            deleteVo.setId(id);
            deleteVo.setIsAvailable(commonContants.NOT_AVAILABLE);
            deleteVo.setModifyUser("yy");
            deleteVo.setModifyTime(new Date());
            tShopService.updateById(deleteVo);
            // TODO: 2020/12/30 是否需要同时下架职务
        }
        //修改信息
        else if (commonContants.IS_UPDATE.equals(type)){
            TShop updateVo = new TShop();
            updateVo.setId(id);
            updateVo.setShopName(shopName);
            updateVo.setShopParentId(shopParentId);
            updateVo.setModifyTime(new Date());
            updateVo.setModifyUser("yy");
            tShopService.updateById(updateVo);
        }
    }

    /**
     * 职务信息添加
     *   职务-门店信息关联
     * @param orgName
     */
    public void addOrgInfo(String orgName,Integer shopId) throws CustomException {
        try {
            //新增职务信息
            TOrg addVo = new TOrg();
            Date date = new Date();
            addVo.setOrgName(orgName);
            addVo.setIsAvailable(commonContants.IS_AVAILABLE);
            addVo.setCreateTime(date);
            addVo.setCreateUser("yy");
            addVo.setModifyTime(date);
            addVo.setModifyUser("yy");
            tOrgService.save(addVo);
            //职务-门店信息关联
            //查询生成的职务id
            List<TOrg> list = tOrgService.list(new LambdaQueryWrapper<TOrg>().eq(TOrg::getCreateTime, date));
            if (BeanUtil.isNotEmpty(list)){
                TShopOrg shopOrg = new TShopOrg();
                shopOrg.setShopId(shopId);
                shopOrg.setOrgId(list.get(0).getId());
                shopOrg.setCreateTime(date);
                shopOrg.setCreateUser("yy");
                shopOrg.setModifyTime(date);
                shopOrg.setModifyUser("yy");
                tShopOrgService.save(shopOrg);
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new CustomException(ProjectContant.ERROR_500,"职务添加失败！");
        }

    }

    /**
     * 职务查询
     * @return
     */
    public List<OrgResVo> getOrgInfo() {
        return tOrgMapper.getOrgList();
    }

    /**
     * 职务修改
     * @param id
     * @param type
     * @param orgName
     * @param orgParentId
     */
    public void updateOrgInfo(Integer id, String type, String orgName, Integer orgParentId) {
        //职务下架
        if (commonContants.IS_DELETE.equals(type)){
            TOrg deleteVo = new TOrg();
            deleteVo.setId(id);
            deleteVo.setIsAvailable(commonContants.NOT_AVAILABLE);
            deleteVo.setModifyUser("yy");
            deleteVo.setModifyTime(new Date());
            tOrgService.updateById(deleteVo);
        }
        //修改信息
        else if (commonContants.IS_UPDATE.equals(type)){
            TOrg updateVo = new TOrg();
            updateVo.setId(id);
            updateVo.setOrgName(orgName);
            updateVo.setOrgParentId(orgParentId);
            updateVo.setModifyTime(new Date());
            updateVo.setModifyUser("yy");
            tOrgService.updateById(updateVo);
        }
    }

    /**
     * 角色添加
     * @param orgName
     */
    public void addRoleInfo(String orgName) {
        TRole tRole = new TRole();
        Date date = new Date();
        tRole.setIsAvailable(commonContants.IS_AVAILABLE);
        tRole.setOrgName(orgName);
        tRole.setCreateTime(date);
        tRole.setCreateUser("yy");
        tRole.setModifyTime(date);
        tRole.setModifyUser("yy");
        tRoleService.save(tRole);
    }

    /**
     * 角色查询
     */
    public List<TRole> getRoleInfo() {
       return tRoleService.list(new LambdaQueryWrapper<TRole>()
               .eq(TRole::getIsAvailable,commonContants.IS_AVAILABLE));
    }

    /**
     * 角色更新
     * @param id
     * @param type
     * @param orgName
     */
    public void updateRoleInfo(Integer id, String type, String orgName) {
        //角色下架
        if (commonContants.IS_DELETE.equals(type)){
            TRole deleteVo = new TRole();
            deleteVo.setId(id);
            deleteVo.setIsAvailable(commonContants.NOT_AVAILABLE);
            deleteVo.setModifyUser("yy");
            deleteVo.setModifyTime(new Date());
            tRoleService.updateById(deleteVo);
        }
        //修改信息
        else if (commonContants.IS_UPDATE.equals(type)){
            TRole updateVo = new TRole();
            updateVo.setId(id);
            updateVo.setOrgName(orgName);
            updateVo.setModifyTime(new Date());
            updateVo.setModifyUser("yy");
            tRoleService.updateById(updateVo);
        }
    }
}

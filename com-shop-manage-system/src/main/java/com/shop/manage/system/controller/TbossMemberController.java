package com.shop.manage.system.controller;

import com.shop.manage.system.business.TboosMemberBusiness;
import com.shop.manage.system.entity.TRole;
import com.shop.manage.system.entity.TShop;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.vo.MemberResVo;
import com.shop.manage.system.vo.OrgResVo;
import com.shop.manage.system.vo.UserInfoAddVo;
import com.shop.manage.system.vo.MemberUpdateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 老板会员中心模块 控制层
 * @author ytl
 * @crate 2020/12/29
 */
@RestController
@RequestMapping("/boss/")
public class TbossMemberController {
    @Autowired
    private TboosMemberBusiness tboosMemberBusiness;


    /**
     * 老板会员中心模块 门店添加
     * @param shopName
     * @return
     */
    @GetMapping("addShopInfo")
    public void addShopInfo(@RequestParam(value = "shopName")String shopName){
        tboosMemberBusiness.addShopInfo(shopName);
    }

    /**
     * 老板会员中心模块 门店查询
     * @return
     */
    @GetMapping("getShopInfo")
    public List<TShop> getShopInfo(){
        return tboosMemberBusiness.getShopInfo();
    }

    /**
     * 老板会员中心模块 门店修改
     * @param type 传00：门店状态下架，传01：门店信息修改
     * @param shopName
     * @param shopParentId
     * @return
     */
    @GetMapping("updateShopInfo")
    public void updateShopInfo(@RequestParam(value = "type")String type,
                                   @RequestParam(value = "id")Integer id,
                                   @RequestParam(value = "shopName",required = false)String shopName,
                                   @RequestParam(value = "shopParentId",required = false)Integer shopParentId){
         tboosMemberBusiness.updateShopInfo(type,id,shopName,shopParentId);
    }

    /**
     * 老板会员中心模块 职务添加
     * @param orgName
     * @param shopId
     * @return
     */
    @GetMapping("addOrgInfo")
    public void addOrgInfo(@RequestParam(value = "orgName")String orgName,
                           @RequestParam(value = "shopId")Integer shopId) throws CustomException {
        tboosMemberBusiness.addOrgInfo(orgName,shopId);
    }

    /**
     * 老板会员中心模块 职务查询
     * @return
     */
    @GetMapping("getOrgInfo")
    public List<OrgResVo> getOrgInfo(){
        return tboosMemberBusiness.getOrgInfo();
    }

    /**
     * 老板会员中心模块 职务修改
     * @param id
     * @param type 传00：职务状态下架，传01：职务信息修改
     * @param orgName
     * @param orgParentId
     */
    @GetMapping("updateOrgInfo")
    public void updateOrgInfo(@RequestParam(value = "id")Integer id,
                              @RequestParam(value = "type") String type,
                              @RequestParam(value = "orgName",required = false)String orgName,
                              @RequestParam(value = "orgParentId",required = false)Integer orgParentId){
         tboosMemberBusiness.updateOrgInfo(id,type,orgName,orgParentId);
    }


    /**
     * 老板会员中心模块 角色添加
     * @param orgName
     * @return
     */
    @GetMapping("addRoleInfo")
    public void addRoleInfo(@RequestParam(value = "orgName")String orgName) {
        tboosMemberBusiness.addRoleInfo(orgName);
    }
    /**
     * 老板会员中心模块 角色查询
     * @return
     */
    @GetMapping("getRoleInfo")
    public List<TRole> getRoleInfo() {
        return tboosMemberBusiness.getRoleInfo();
    }

    /**
     * 老板会员中心模块 角色更新
     * @param id
     * @param type 传00：角色状态下架，传01：角色信息修改
     * @param orgName 角色名称
     */
    @GetMapping("updateRoleInfo")
    public void updateRoleInfo(@RequestParam(value = "id")Integer id,
                               @RequestParam(value = "type") String type,
                               @RequestParam(value = "orgName",required = false)String orgName) {
         tboosMemberBusiness.updateRoleInfo(id,type,orgName);
    }


    /**
     * 老板会员中心模块 会员中心添加接口
     * @return
     */
    @PostMapping("addMember")
    public void addMember(@RequestBody @Valid UserInfoAddVo addVo) throws CustomException {
        tboosMemberBusiness.addMember(addVo);
    }

    /**
     * 老板会员中心模块 会员中心查询接口
     * @param id 账号id
     * @return
     */
    @GetMapping("getMember")
    public MemberResVo getMember(@RequestParam(value = "id")Integer id) {
         return tboosMemberBusiness.getMember(id);
    }

    /**
     * 老板会员中心模块 会员中心修改接口
     * @param updateVo
     * @return
     */
    @PostMapping("updateMember")
    public void updateMember(@RequestBody @Valid MemberUpdateVo updateVo) {
         tboosMemberBusiness.updateMember(updateVo);
    }




}

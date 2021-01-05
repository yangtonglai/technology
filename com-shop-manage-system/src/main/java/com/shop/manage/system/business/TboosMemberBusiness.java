package com.shop.manage.system.business;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shop.manage.system.contant.IndexContants;
import com.shop.manage.system.contant.ProjectContant;
import com.shop.manage.system.contant.commonContants;
import com.shop.manage.system.dto.OrderSortResDto;
import com.shop.manage.system.entity.*;
import com.shop.manage.system.exception.CustomException;
import com.shop.manage.system.mapper.TOrgMapper;
import com.shop.manage.system.mapper.TShopOrderMapper;
import com.shop.manage.system.mapper.TUserMapper;
import com.shop.manage.system.service.*;
import com.shop.manage.system.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private TUserService tUserService;
    @Autowired
    private TUserRoleService tUserRoleService;
    @Autowired
    private TUserOrgService tUserOrgService;
    @Autowired
    private TUserMapper tUserMapper;
    @Autowired
    private TShopOrderMapper tShopOrderMapper;
    @Autowired
    private TGroupNoticeUserService tGroupNoticeUserService;
    @Autowired
    private TNoticeService tNoticeService;

    /**
     * 账号注册：
     * 1-账号基础信息注册
     * 2-账号角色关联
     * 3-账号-职务关联
     * @param addVo
     */
    @Transactional(rollbackFor = Exception.class)
    public void addMember(UserInfoAddVo addVo) throws CustomException {
        //账号注册
        TUser tUser = new TUser();
        Date date = new Date();
        try {
            if (BeanUtil.isNotEmpty(addVo.getLoginName())){
                tUser.setLoginName(addVo.getLoginName());
            }
            tUser.setNickName(addVo.getNickName());
            tUser.setIsAvailable(commonContants.IS_AVAILABLE);
            if (BeanUtil.isNotEmpty(addVo.getIdCard())){
                tUser.setIdCard(addVo.getIdCard());
            }
            if (BeanUtil.isNotEmpty(addVo.getContactPhone())){
                tUser.setContactPhone(addVo.getContactPhone());
            }
            if (BeanUtil.isNotEmpty(addVo.getFactName())){
                tUser.setFactName(addVo.getFactName());
            }
            if (BeanUtil.isNotEmpty(addVo.getHeadImageUrl())){
                tUser.setHeadImageUrl(addVo.getHeadImageUrl());
            }
            if (BeanUtil.isNotEmpty(addVo.getUserAddress())){
                tUser.setUserAddress(addVo.getUserAddress());
            }
            tUser.setCreateTime(date);
            tUser.setCreateUser("yy");
            tUser.setModifyTime(date);
            tUser.setModifyUser("yy");
            tUser.setMemberLevel(addVo.getMemberLevel());
            tUserMapper.saveMember(tUser);
            //查询生成得id
            Integer id = tUser.getId();
            if (BeanUtil.isEmpty(id)){
                throw new CustomException(ProjectContant.ERROR_500,"注册账号错误！");
            }
            //账号角色关联
            addUserRole(id ,addVo.getRoleId(),date);
            //账职务绑定
            addUserOrg(id ,addVo.getOrgId(),date);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new CustomException(ProjectContant.ERROR_500,"注册账号错误！");
        }

    }

    /**
     * 账号-角色绑定
     * @param userId
     * @param roleId
     * @param date
     */
    private void addUserRole(Integer userId,Integer roleId,Date date){
        TUserRole data = new TUserRole();
        data.setRoleId(roleId);
        data.setUserId(userId);
        data.setCreateTime(date);
        data.setCreateUser("yy");
        data.setModifyTime(date);
        data.setModifyUser("yy");
        tUserRoleService.save(data);
    }
    /**
     * 职务-账号绑定
     * @param userId
     * @param orgId
     * @param date
     */
    private void addUserOrg(Integer userId,Integer orgId,Date date){
        TUserOrg data = new TUserOrg();
        data.setOrgId(orgId);
        data.setUserId(userId);
        data.setCreateTime(date);
        data.setCreateUser("yy");
        data.setModifyTime(date);
        data.setModifyUser("yy");
        tUserOrgService.save(data);
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
    @Transactional(rollbackFor = Exception.class)
    public void addOrgInfo(String orgName,Integer shopId) throws CustomException {
        try {
            //新增职务信息
            TOrg addVo = new TOrg();
            Date date = new Date();
            // TODO: 2021/1/4 userid
            addVo.setOrgName(orgName);
            addVo.setIsAvailable(commonContants.IS_AVAILABLE);
            addVo.setCreateTime(date);
            addVo.setCreateUser("yy");
            addVo.setModifyTime(date);
            addVo.setModifyUser("yy");
            tOrgMapper.saveOrg(addVo);
            Integer id = addVo.getId();
            //职务-门店信息关联
            if (BeanUtil.isEmpty(id)){
                throw new CustomException(ProjectContant.ERROR_500,"职务添加失败！");
            }
            TShopOrg shopOrg = new TShopOrg();
            shopOrg.setShopId(shopId);
            shopOrg.setOrgId(id);
            shopOrg.setCreateTime(date);
            shopOrg.setCreateUser("yy");
            shopOrg.setModifyTime(date);
            shopOrg.setModifyUser("yy");
            tShopOrgService.save(shopOrg);

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

    /**
     * 会员中心查询接口
     * @param id
     * @return
     */
    public MemberResVo getMember(Integer id) {
        return tUserMapper.getMember(id);
    }

    /**
     * 会员中心修改接口
     * @param updateVo
     */
    public void updateMember(MemberUpdateVo updateVo) {
        //账号注销
        if (commonContants.IS_DELETE.equals(updateVo.getType())){
            TUser tUser = new TUser();
            tUser.setId(updateVo.getId());
            tUser.setIsAvailable(commonContants.NOT_AVAILABLE);
            tUser.setModifyTime(new Date());
            tUser.setModifyUser("yy");
            tUserService.updateById(tUser);
        }
        //修改信息
        else if (commonContants.IS_UPDATE.equals(updateVo.getType())){
            TUser tUser = new TUser();
            tUser.setId(updateVo.getId());
            if (BeanUtil.isNotEmpty(updateVo.getLoginName())){
                tUser.setLoginName(updateVo.getLoginName());
            }
            if (BeanUtil.isNotEmpty(updateVo.getNickName())){
                tUser.setNickName(updateVo.getNickName());
            }
            if (BeanUtil.isNotEmpty(updateVo.getIdCard())){
                tUser.setIdCard(updateVo.getIdCard());
            }
            if (BeanUtil.isNotEmpty(updateVo.getContactPhone())){
                tUser.setContactPhone(updateVo.getContactPhone());
            }
            if (BeanUtil.isNotEmpty(updateVo.getFactName())){
                tUser.setFactName(updateVo.getFactName());
            }
            if (BeanUtil.isNotEmpty(updateVo.getMemberLevel())){
                tUser.setMemberLevel(updateVo.getMemberLevel());
            }
            if (BeanUtil.isNotEmpty(updateVo.getHeadImageUrl())){
                tUser.setHeadImageUrl(updateVo.getHeadImageUrl());
            }
            if (BeanUtil.isNotEmpty(updateVo.getUserAddress())){
                tUser.setUserAddress(updateVo.getUserAddress());
            }
            tUser.setModifyUser("yy");
            tUser.setModifyTime(new Date());
            tUserService.updateById(tUser);
        }

    }

    /**
     * 订单分类查询
     * @return
     */
    public Map<Integer,List<ShopOrderResVo>> getOrderSort() {
        List<OrderSortResDto> orderList = tShopOrderMapper.getProductSort();
        if (CollUtil.isEmpty(orderList)){
            return new HashMap<>();
        }
        //根据订单id、分组
        Map<Integer, List<OrderSortResDto>> orderMap = orderList.
                stream().collect(Collectors.groupingBy(OrderSortResDto::getId));
        //获取订单的图片list
        Map<Integer,List<String>> imageMap = new HashMap<>();
        for (OrderSortResDto resVo : orderList) {
            List<OrderSortResDto> orderSortResDtos = orderMap.get(resVo.getId());
            if (CollUtil.isNotEmpty(orderSortResDtos)){
                List<String> collect = orderSortResDtos.stream().map(OrderSortResDto::getProductImageUrl)
                        .collect(Collectors.toList());
                imageMap.put(resVo.getId(),collect);
            }
        }
        //数据封装
        List<ShopOrderResVo> data = new ArrayList<>();
        for (OrderSortResDto resDto : orderList) {
            ShopOrderResVo vo = new ShopOrderResVo();
            if (MapUtil.isNotEmpty(imageMap)&&BeanUtil.isNotEmpty(imageMap.get(resDto.getId()))){
                vo.setProductImageUrl(imageMap.get(resDto.getId()));
            }
            BeanUtil.copyProperties(resDto,vo);
            data.add(vo);
        }
        return data.stream().collect(Collectors.groupingBy(ShopOrderResVo::getStatus));
    }

    /**
     * 通知公告
     * @param userId
     * @return
     */
    public Map<Integer, String> getNoticeInfo(String userId) {
        List<TGroupNoticeUser> users = tGroupNoticeUserService.list(new LambdaQueryWrapper<TGroupNoticeUser>()
                .eq(TGroupNoticeUser::getUserId, userId).eq(TGroupNoticeUser::getIsRead, IndexContants.NOT_READ));
        if (CollUtil.isEmpty(users)){
            return new HashMap<>();
        }
        Set<Integer> notices = users.stream().map(TGroupNoticeUser::getNoticeId).collect(Collectors.toSet());
        return tNoticeService.listByIds(notices).stream().collect(Collectors.toMap(TNotice::getId,TNotice::getContentInfo));
    }
}

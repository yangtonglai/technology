package com.shop.manage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.manage.system.entity.TOrg;
import com.shop.manage.system.vo.OrgResVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangtl
 * @since 2020-12-29
 */
public interface TOrgMapper extends BaseMapper<TOrg> {

    /**
     * 查询职务列表
     * @return
     */
    List<OrgResVo> getOrgList();
    /**
     * 插入职位
     */
    Integer saveOrg(TOrg tOrg);
}

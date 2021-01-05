package com.shop.manage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *  群组-人员-消息表
 * </p>
 *
 * @author yangtl
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TGroupNoticeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 群组id
     */
    private Integer groupId;

    /**
     * 人员id
     */
    private Integer userId;

    /**
     * 通知id
     */
    private Integer noticeId;

    /**
     * 是否已读 0:未读 1：已读
     */
    private String isRead;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 修改人
     */
    private String modifyUser;


}

package com.ruoyi.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionSignup extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 报名ID */
    private Long signupId;

    /** 关联赛事ID */
    @Excel(name = "关联赛事ID")
    private Long competitionId;

    /** 报名用户ID（关联sys_user.user_id） */
    @Excel(name = "报名用户ID", readConverterExp = "关=联sys_user.user_id")
    private Long userId;

    /** 报名状态：0-待审核 1-已通过 2-已拒绝 3-已取消 */
    @Excel(name = "报名状态：0-待审核 1-已通过 2-已拒绝 3-已取消")
    private Integer signupStatus;

    /** 审核人ID（管理员ID） */
    private Long auditBy;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 删除标识：0-未删除 1-已删除 */
    private Integer delFlag;

}
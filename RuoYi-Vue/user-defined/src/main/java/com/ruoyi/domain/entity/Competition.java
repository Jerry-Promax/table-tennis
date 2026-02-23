package com.ruoyi.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competition extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 赛事ID */
    private Long competitionId;

    /** 赛事名称 */
    @Excel(name = "赛事名称")
    private String competitionName;

    /** 报名开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signupStart;

    /** 报名结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "报名结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signupEnd;

    /** 赛事开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "赛事开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 赛事结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "赛事结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 赛事状态：0-未开始 1-报名中 2-进行中 3-已结束 4-已取消 */
    @Excel(name = "赛事状态：0-未开始 1-报名中 2-进行中 3-已结束 4-已取消")
    private Integer status;

    /** 赛事规则说明 */
    @Excel(name = "赛事规则说明")
    private String ruleDesc;
    /** 最大报名人数（0表示不限） */
    @Excel(name = "最大报名人数", readConverterExp = "0=表示不限")
    private Long maxSignup;

    /** 当前已报名人数 */
    @Excel(name = "当前已报名人数")
    private Long currentSignup;
    /** 删除标识：0-未删除 1-已删除 */
    private Integer delFlag;
}
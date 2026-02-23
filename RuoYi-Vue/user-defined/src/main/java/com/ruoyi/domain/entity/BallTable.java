package com.ruoyi.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import nl.basjes.parse.useragent.yauaa.shaded.org.apache.commons.lang3.builder.ToStringBuilder;
import nl.basjes.parse.useragent.yauaa.shaded.org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BallTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 球台ID（主键） */
    private Long tableId;

    /** 球台编号（如T001、T002） */
    @Excel(name = "球台编号", readConverterExp = "如=T001、T002")
    private String tableCode;

    /** 球台类型（如普通台/VIP台/比赛台） */
    @Excel(name = "球台类型", readConverterExp = "如=普通台/VIP台/比赛台")
    private String tableType;

    /** 球台状态（0=空闲 1=预定 2=使用中 3=维修） */
    @Excel(name = "球台状态", readConverterExp = "0==空闲,1==预定,2==使用中,3==维修")
    private String tableStatus;
    /** 删除标志（0=正常 1=删除） */
    private String delFlag;
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tableId", getTableId())
                .append("tableCode", getTableCode())
                .append("tableType", getTableType())
                .append("tableStatus", getTableStatus())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
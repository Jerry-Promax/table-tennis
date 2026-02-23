package com.ruoyi.domain.entity;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BallTablePrice extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long priceId;
    private Long tableId;
    private Time startTime;
    private Time endTime;
    private String price;
    private Date effectiveTime;
    private Date expireTime;
    private String delFlag;

}
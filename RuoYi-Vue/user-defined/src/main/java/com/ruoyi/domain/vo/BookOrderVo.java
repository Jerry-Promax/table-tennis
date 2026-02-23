package com.ruoyi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrderVo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 预约订单ID */
    private Long orderId;

    /** 预约订单号（唯一，如：Y202501010001） */
    @Excel(name = "预约订单号", readConverterExp = "唯=一，如：Y202501010001")
    private String orderNo;

    /** 场地ID */
    @Excel(name = "场地ID")
    private Long venueId;

    /** 预约用户ID（仅存储ID，无外键关联） */
    @Excel(name = "预约用户ID", readConverterExp = "仅=存储ID，无外键关联")
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 新增时区，避免时间偏移 + 改为时分秒格式
    @Excel(name = "预约开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss") // Excel导出也显示时分秒
    private Date reserveStart;

    /** 预约结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 同上
    @Excel(name = "预约结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reserveEnd;

    /** 预约时长（分钟） */
    @Excel(name = "预约时长", readConverterExp = "分=钟")
    private Long duration;

    /** 预约状态：0-待审核 1-已通过 2-已拒绝 3-已取消 4-已完成 */
    @Excel(name = "预约状态：0-待审核 1-已通过 2-已拒绝 3-已取消 4-已完成")
    private Integer status;

    /** 拒绝/取消原因 */
    @Excel(name = "拒绝/取消原因")
    private String reason;

    /** 支付金额 */
    @Excel(name = "支付金额")
    private BigDecimal payAmount;

    /** 支付状态：0-未支付 1-已支付 2-已退款 */
    @Excel(name = "支付状态：0-未支付 1-已支付 2-已退款")
    private Integer payStatus;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    private Integer delFlag;
    private String nickName;
    private String tableCode;
}
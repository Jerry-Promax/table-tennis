package com.tts.service;

import com.tts.domain.entity.BookOrder;
import com.tts.domain.vo.BookOrderVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-30
 */
public interface IBookOrderService {
    /**
     * 查询预约订单
     *
     * @param orderId 预约订单主键
     * @return 预约订单
     */
    public BookOrderVo selectBookOrderByOrderId(Long orderId);

    /**
     * 查询预约订单列表
     *
     * @param bookOrder 预约订单
     * @return 预约订单集合
     */
    public List<BookOrderVo> selectBookOrderList(BookOrder bookOrder);

    /**
     * 新增预约订单
     *
     * @param bookOrder 预约订单
     * @return 结果
     */
    public int insertBookOrder(BookOrder bookOrder);

    /**
     * 修改预约订单
     *
     * @param bookOrder 预约订单
     * @return 结果
     */
    public int updateBookOrder(BookOrder bookOrder);

    /**
     * 批量删除预约订单
     *
     * @param orderIds 需要删除的预约订单主键集合
     * @return 结果
     */
    public int deleteBookOrderByOrderIds(Long[] orderIds);

    /**
     * 删除预约订单信息
     *
     * @param orderId 预约订单主键
     * @return 结果
     */
    public int deleteBookOrderByOrderId(Long orderId);

    /**
     * 支付成功处理
     * @param orderNo 订单号
     * @param payAmount 支付金额
     * @param payTime 支付时间
     * @return 结果
     */
    public int paySuccess(String orderNo, BigDecimal payAmount, Date payTime);
}

package com.ruoyi.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.domain.entity.BallTable;
import com.ruoyi.domain.entity.BookOrder;
import com.ruoyi.domain.vo.BookOrderVo;
import com.ruoyi.mapper.BallTableMapper;
import com.ruoyi.mapper.BookOrderMapper;
import com.ruoyi.service.IBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-30
 */
@Service
public class BookOrderServiceImpl implements IBookOrderService {
    @Autowired
    private BookOrderMapper bookOrderMapper;
    @Autowired
    private BallTableMapper ballTableMapper;

    /**
     * 查询预约订单
     *
     * @param orderId 预约订单主键
     * @return 预约订单
     */
    @Override
    public BookOrderVo selectBookOrderByOrderId(Long orderId)
    {
        return bookOrderMapper.selectBookOrderByOrderId(orderId);
    }

    /**
     * 查询预约订单列表
     *
     * @param bookOrder 预约订单
     * @return 预约订单
     */
    @Override
    public List<BookOrderVo> selectBookOrderList(BookOrder bookOrder)
    {
        return bookOrderMapper.selectBookOrderList(bookOrder);
    }

    /**
     * 新增预约订单
     *
     * @param bookOrder 预约订单
     * @return 结果
     */
    @Override
    @Transactional
    public int insertBookOrder(BookOrder bookOrder)
    {
        bookOrder.setCreateTime(DateUtils.getNowDate());
        bookOrder.calculateReserveEnd();
//        bookOrder.getDuration() * ;
//        bookOrder.setDuration(bookOrder.getReserveEnd().getTime() - bookOrder.getReserveStart().getTime()/(1000*60));
        int n = bookOrderMapper.insertBookOrder(bookOrder);
        bookOrder.setOrderNo(genOrderNo(bookOrder.getOrderId()));
        int m = bookOrderMapper.updateBookOrder(bookOrder);
//        BallTable ballTable = new BallTable();
//        ballTable.setTableId(bookOrder.getVenueId());
//        ballTable.setTableStatus("1");
//        ballTableMapper.updateBallTable(ballTable);
        return n + m;
    }
    public String genOrderNo(Long orderId){
//        String orderNo ="Y" + DateUtils.getDateStr() + orderId;
//        StringBuilder sb = new StringBuilder(orderNo.length());
//        sb.append(orderNo);
//        if (orderNo.length() < 13){
//            if (orderId < 10){
//                sb.insert(9,"000");
//            }else if (orderId < 100){
//                sb.insert(9,"00");
//            }else if (orderId < 1000){
//                sb.insert(9,"0");
//            }
//        }
//        return sb.toString();
        String dateStr = DateUtils.getDateStr();
        String orderIdStr = String.format("%04d", orderId);
        return "Y" + dateStr + orderIdStr;
    }

    public static void main(String[] args) {
        BookOrderServiceImpl bookOrderService = new BookOrderServiceImpl();
        System.out.println(bookOrderService.genOrderNo(101L));
    }
    /**
     * 修改预约订单
     *
     * @param bookOrder 预约订单
     * @return 结果
     */
    @Override
    @Transactional
    public int updateBookOrder(BookOrder bookOrder)
    {
        bookOrder.setUpdateTime(DateUtils.getNowDate());
        int n = bookOrderMapper.updateBookOrder(bookOrder);
        // 审核通过后，将球桌状态设置为已预约
        if (bookOrder.getStatus() == 1) {
            BallTable ballTable = new BallTable();
            ballTable.setTableId(bookOrder.getVenueId());
            ballTable.setTableStatus("1");
            ballTableMapper.updateBallTable(ballTable);
        }
        return n;
    }

    /**
     * 批量删除预约订单
     *
     * @param orderIds 需要删除的预约订单主键
     * @return 结果
     */
    @Override
    public int deleteBookOrderByOrderIds(Long[] orderIds)
    {
        return bookOrderMapper.deleteBookOrderByOrderIds(orderIds);
    }

    /**
     * 删除预约订单信息
     *
     * @param orderId 预约订单主键
     * @return 结果
     */
    @Override
    public int deleteBookOrderByOrderId(Long orderId)
    {
        return bookOrderMapper.deleteBookOrderByOrderId(orderId);
    }

    /**
     * 支付成功处理
     * @param orderNo 订单号
     * @param payAmount 支付金额
     * @param payTime 支付时间
     * @return 结果
     */
    @Override
    @Transactional
    public int paySuccess(String orderNo, BigDecimal payAmount, Date payTime) {
        BookOrder query = new BookOrder();
        query.setOrderNo(orderNo);
        List<BookOrderVo> list = bookOrderMapper.selectBookOrderList(query);
        if (list.isEmpty()) {
            throw new RuntimeException("订单不存在");
        }
        BookOrderVo orderVo = list.get(0);
        BookOrder update = new BookOrder();
        update.setOrderId(orderVo.getOrderId());
        update.setPayStatus(1); // 已支付
        update.setPayAmount(payAmount);
        update.setPayTime(payTime);
        update.setUpdateTime(DateUtils.getNowDate());
        return bookOrderMapper.updateBookOrder(update);
    }
}
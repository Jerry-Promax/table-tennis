package com.ruoyi.mapper;

import com.ruoyi.domain.entity.BookOrder;
import com.ruoyi.domain.vo.BookOrderVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-30
 */
@Mapper
public interface BookOrderMapper {
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
     * 删除预约订单
     *
     * @param orderId 预约订单主键
     * @return 结果
     */
    public int deleteBookOrderByOrderId(Long orderId);

    /**
     * 批量删除预约订单
     *
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookOrderByOrderIds(Long[] orderIds);

    /**
     * 统计时间段内的订单数量（状态为已通过或已完成）
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订单数量
     */
    int countBookOrderByTimeRange(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 统计每日营收
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 每日营收列表
     */
    @MapKey("revenue_date")
    List<Map<String, Object>> selectDailyRevenue(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
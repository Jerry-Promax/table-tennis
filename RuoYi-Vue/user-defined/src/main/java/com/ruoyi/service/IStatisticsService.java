package com.ruoyi.service;

import java.util.Map;

/**
 * 统计服务接口
 */
public interface IStatisticsService {
    /**
     * 获取三个时间段的场地使用统计
     * @return 统计结果
     */
    Map<String, Object> getTimePeriodUsageStats();

    /**
     * 获取每日营业额统计
     * @param days 天数
     * @return 统计结果
     */
    Map<String, Object> getDailyRevenueStats(int days);
}

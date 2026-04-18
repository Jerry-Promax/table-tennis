package com.tts.controller;

import com.tts.common.core.controller.BaseController;
import com.tts.common.core.domain.AjaxResult;
import com.tts.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 数据统计控制器
 *
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private IStatisticsService statisticsService;

    /**
     * 获取三个时间段的场地使用数量
     */
    @GetMapping("/usage/time-period")
    public AjaxResult getTimePeriodUsageStats() {
        Map<String, Object> stats = statisticsService.getTimePeriodUsageStats();
        return AjaxResult.success(stats);
    }

    /**
     * 获取每日营业额
     */
    @GetMapping("/revenue/daily")
    public AjaxResult getDailyRevenueStats(@RequestParam(value = "days", defaultValue = "7") int days) {
        Map<String, Object> stats = statisticsService.getDailyRevenueStats(days);
        return AjaxResult.success(stats);
    }
}

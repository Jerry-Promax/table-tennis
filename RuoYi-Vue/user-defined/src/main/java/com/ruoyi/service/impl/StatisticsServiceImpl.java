package com.ruoyi.service.impl;

import com.ruoyi.mapper.BookOrderMapper;
import com.ruoyi.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 统计服务实现类
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private BookOrderMapper bookOrderMapper;

    @Override
    public Map<String, Object> getTimePeriodUsageStats() {
        // Morning: 06:00-12:00, Afternoon: 12:00-18:00, Evening: 18:00-24:00
        int morning = bookOrderMapper.countBookOrderByTimeRange("08:00:00", "11:59:59");
        int afternoon = bookOrderMapper.countBookOrderByTimeRange("12:00:00", "17:59:59");
        int evening = bookOrderMapper.countBookOrderByTimeRange("18:00:00", "23:59:59");

        Map<String, Object> result = new HashMap<>();
        List<String> categories = Arrays.asList("上午 (08:00-12:00)", "下午 (12:00-18:00)", "晚上 (18:00-24:00)");
        List<Integer> data = Arrays.asList(morning, afternoon, evening);
        
        result.put("categories", categories);
        result.put("data", data);
        return result;
    }

    @Override
    public Map<String, Object> getDailyRevenueStats(int days) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(days - 1); // include today
        
        String startDateStr = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00";
        String endDateStr = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 23:59:59";
        
        List<Map<String, Object>> dbList = bookOrderMapper.selectDailyRevenue(startDateStr, endDateStr);

        Map<String, BigDecimal> revenueMap = new HashMap<>();
        for (Map<String, Object> row : dbList) {
            String date = null;
            BigDecimal amount = BigDecimal.ZERO;

            // 遍历 Map 处理 key 大小写问题 (MyBatis 返回的 Map key 可能是大写)
            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String key = entry.getKey();
                if ("revenue_date".equalsIgnoreCase(key) || "date".equalsIgnoreCase(key)) {
                    Object dateObj = entry.getValue();
                    if (dateObj != null) {
                        date = dateObj.toString();
                    }
                } else if ("total_amount".equalsIgnoreCase(key) || "amount".equalsIgnoreCase(key)) {
                    Object amountObj = entry.getValue();
                    if (amountObj != null) {
                        if (amountObj instanceof BigDecimal) {
                            amount = (BigDecimal) amountObj;
                        } else if (amountObj instanceof Number) {
                            amount = new BigDecimal(((Number) amountObj).toString());
                        }
                    }
                }
            }

            if (date != null) {
                revenueMap.put(date, amount);
            }
        }
        
        List<String> dateList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        
        for (int i = 0; i < days; i++) {
            LocalDate date = start.plusDays(i);
            String dateStr = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dateList.add(dateStr);
            amountList.add(revenueMap.getOrDefault(dateStr, BigDecimal.ZERO));
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("dates", dateList);
        result.put("amounts", amountList);
        return result;
    }
}

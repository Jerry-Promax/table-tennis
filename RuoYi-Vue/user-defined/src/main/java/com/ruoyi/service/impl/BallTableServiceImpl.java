package com.ruoyi.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.domain.entity.BallTable;
import com.ruoyi.domain.entity.BallTablePrice;
import com.ruoyi.mapper.BallTableMapper;
import com.ruoyi.service.IBallTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-21
 */
@Service
public class BallTableServiceImpl implements IBallTableService {
    public static final Time MORNING = Time.valueOf(LocalTime.of(8, 0, 0));
    public static final Time AFTERNOON = Time.valueOf(LocalTime.of(12, 0, 0));
    public static final Time NIGHT = Time.valueOf(LocalTime.of(18, 0, 0));
    public static final Time MIDNIGHT = Time.valueOf(LocalTime.of(23, 59, 59));

    @Autowired
    private BallTableMapper ballTableMapper;

    /**
     * 查询乒乓球台基础信息
     *
     * @param tableId 乒乓球台基础信息主键
     * @return 乒乓球台基础信息
     */
    @Override
    public BallTable selectBallTableByTableId(Long tableId)
    {
        return ballTableMapper.selectBallTableByTableId(tableId);
    }

    /**
     * 查询乒乓球台基础信息列表
     *
     * @param ballTable 乒乓球台基础信息
     * @return 乒乓球台基础信息
     */
    @Override
    public List<BallTable> selectBallTableList(BallTable ballTable)
    {
        return ballTableMapper.selectBallTableList(ballTable);
    }

    /**
     * 新增乒乓球台基础信息
     *
     * @param ballTable 乒乓球台基础信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBallTable(BallTable ballTable)
    {
        ballTable.setCreateTime(DateUtils.getNowDate());
        int n = ballTableMapper.insertBallTable(ballTable);
        Long tableId = ballTable.getTableId();
        BallTablePrice ballTablePriceMorning = new BallTablePrice();
        ballTablePriceMorning.setTableId(tableId);
        ballTablePriceMorning.setStartTime(MORNING);
        ballTablePriceMorning.setEndTime(AFTERNOON);
        int n1 = ballTableMapper.insertBallTablePrice(ballTablePriceMorning);
        BallTablePrice ballTablePriceAfternoon = new BallTablePrice();
        ballTablePriceAfternoon.setTableId(tableId);
        ballTablePriceAfternoon.setStartTime(AFTERNOON);
        ballTablePriceAfternoon.setEndTime(NIGHT);
        int n2 = ballTableMapper.insertBallTablePrice(ballTablePriceAfternoon);
        BallTablePrice ballTablePriceNight = new BallTablePrice();
        ballTablePriceNight.setTableId(tableId);
        ballTablePriceNight.setStartTime(NIGHT);
        ballTablePriceNight.setEndTime(MIDNIGHT);
        int n3 = ballTableMapper.insertBallTablePrice(ballTablePriceNight);


        return n;
    }

    /**
     * 修改乒乓球台基础信息
     *
     * @param ballTable 乒乓球台基础信息
     * @return 结果
     */
    @Override
    public int updateBallTable(BallTable ballTable)
    {
        ballTable.setUpdateTime(DateUtils.getNowDate());
        return ballTableMapper.updateBallTable(ballTable);
    }

    /**
     * 批量删除乒乓球台基础信息
     *
     * @param tableIds 需要删除的乒乓球台基础信息主键
     * @return 结果
     */
    @Override
    public int deleteBallTableByTableIds(Long[] tableIds)
    {
        return ballTableMapper.deleteBallTableByTableIds(tableIds);
    }

    /**
     * 删除乒乓球台基础信息信息
     *
     * @param tableId 乒乓球台基础信息主键
     * @return 结果
     */
    @Override
    public int deleteBallTableByTableId(Long tableId)
    {
        return ballTableMapper.deleteBallTableByTableId(tableId);
    }
    @Override
    public Double[] selectTablePriceByTableId(Long tableId) {
        return ballTableMapper.selectTablePriceByTableId(tableId);
    }

    @Override
    public int updateTablePrice(BallTablePrice ballTablePrice) {
        ballTablePrice.setUpdateTime(DateUtils.getNowDate());
        return ballTableMapper.updateTablePrice(ballTablePrice);
    }
}
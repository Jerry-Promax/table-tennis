package com.tts.mapper;

import com.tts.domain.entity.BallTable;
import com.tts.domain.entity.BallTablePrice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-21
 */
@Mapper
public interface BallTableMapper {
    /**
     * 查询乒乓球台基础信息
     *
     * @param tableId 乒乓球台基础信息主键
     * @return 乒乓球台基础信息
     */
    public BallTable selectBallTableByTableId(Long tableId);

    /**
     * 查询乒乓球台基础信息列表
     *
     * @param ballTable 乒乓球台基础信息
     * @return 乒乓球台基础信息集合
     */
    public List<BallTable> selectBallTableList(BallTable ballTable);

    /**
     * 新增乒乓球台基础信息
     *
     * @param ballTable 乒乓球台基础信息
     * @return 结果
     */
    public int insertBallTable(BallTable ballTable);

    /**
     * 修改乒乓球台基础信息
     *
     * @param ballTable 乒乓球台基础信息
     * @return 结果
     */
    public int updateBallTable(BallTable ballTable);

    /**
     * 删除乒乓球台基础信息
     *
     * @param tableId 乒乓球台基础信息主键
     * @return 结果
     */
    public int deleteBallTableByTableId(Long tableId);

    /**
     * 批量删除乒乓球台基础信息
     *
     * @param tableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBallTableByTableIds(Long[] tableIds);

    /**
     * 查询乒乓球台价格信息
     *
     * @param tableId 乒乓球台基础信息主键
     * @return 乒乓球台基础信息
     */
    public Double[] selectTablePriceByTableId(Long tableId);

    int updateTablePrice(BallTablePrice ballTablePrice);

    int insertBallTablePrice(BallTablePrice ballTablePriceMorning);
}
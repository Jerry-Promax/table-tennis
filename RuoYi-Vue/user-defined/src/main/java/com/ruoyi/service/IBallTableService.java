package com.ruoyi.service;
import java.util.List;
import com.ruoyi.domain.entity.BallTable;
import com.ruoyi.domain.entity.BallTablePrice;

/**
 * 功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-21
 */

public interface IBallTableService {
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
     * 批量删除乒乓球台基础信息
     *
     * @param tableIds 需要删除的乒乓球台基础信息主键集合
     * @return 结果
     */
    public int deleteBallTableByTableIds(Long[] tableIds);

    /**
     * 删除乒乓球台基础信息信息
     *
     * @param tableId 乒乓球台基础信息主键
     * @return 结果
     */
    public int deleteBallTableByTableId(Long tableId);

    public Double[] selectTablePriceByTableId(Long tableId);

    public int updateTablePrice(BallTablePrice ballTablePrice);
}

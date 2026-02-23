package com.ruoyi.mapper;

import com.ruoyi.domain.entity.Competition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@Mapper
public interface CompetitionMapper {
    /**
     * 查询赛事主
     *
     * @param competitionId 赛事主主键
     * @return 赛事主
     */
    public Competition selectCompetitionByCompetitionId(Long competitionId);

    /**
     * 查询赛事主列表
     *
     * @param tCompetition 赛事主
     * @return 赛事主集合
     */
    public List<Competition> selectCompetitionList(Competition tCompetition);

    /**
     * 新增赛事主
     *
     * @param tCompetition 赛事主
     * @return 结果
     */
    public int insertCompetition(Competition tCompetition);

    /**
     * 修改赛事主
     *
     * @param tCompetition 赛事主
     * @return 结果
     */
    public int updateCompetition(Competition tCompetition);

    /**
     * 删除赛事主
     *
     * @param competitionId 赛事主主键
     * @return 结果
     */
    public int deleteCompetitionByCompetitionId(Long competitionId);

    /**
     * 批量删除赛事主
     *
     * @param competitionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionByCompetitionIds(Long[] competitionIds);

    /**
     * 根据赛事名称查询赛事
     * @param competitionName
     * @return
     */
    public Competition selectTCompetitionByCompetitionName(String competitionName);

    /**
     * 更新赛事人数
     * @param competition
     */
    void updateCompetitionJoinPeople(Competition competition);

    /**
     * 增量更新赛事报名人数
     *
     * @param competitionId 赛事ID
     * @param increment 增量值（正数为加，负数为减）
     * @return 影响行数
     */
    int updateSignupCount(@org.apache.ibatis.annotations.Param("competitionId") Long competitionId, @org.apache.ibatis.annotations.Param("increment") int increment);
}
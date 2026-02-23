package com.ruoyi.service;

import com.ruoyi.domain.entity.Competition;

import java.util.List;

/**
 * 功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
public interface ICompetitionService {
    /**
     * 查询赛事主
     *
     * @param competitionId 赛事主主键
     * @return 赛事主
     */
    public Competition selectTCompetitionByCompetitionId(Long competitionId);

    /**
     * 查询赛事主列表
     *
     * @param tCompetition 赛事主
     * @return 赛事主集合
     */
    public List<Competition> selectTCompetitionList(Competition tCompetition);

    /**
     * 新增赛事主
     *
     * @param tCompetition 赛事主
     * @return 结果
     */
    public int insertTCompetition(Competition tCompetition);

    /**
     * 修改赛事主
     *
     * @param tCompetition 赛事主
     * @return 结果
     */
    public int updateTCompetition(Competition tCompetition);

    /**
     * 批量删除赛事主
     *
     * @param competitionIds 需要删除的赛事主主键集合
     * @return 结果
     */
    public int deleteTCompetitionByCompetitionIds(Long[] competitionIds);

    /**
     * 删除赛事主信息
     *
     * @param competitionId 赛事主主键
     * @return 结果
     */
    public int deleteTCompetitionByCompetitionId(Long competitionId);

    Competition selectTCompetitionByCompetitionName(String competitionName);
}

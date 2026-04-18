package com.tts.service.impl;

import com.tts.common.utils.DateUtils;
import com.tts.domain.entity.Competition;
import com.tts.mapper.CompetitionMapper;
import com.tts.service.ICompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@Service
public class CompetitionServiceImpl implements ICompetitionService {
    @Autowired
    private CompetitionMapper competitionMapper;

    /**
     * 查询赛事主
     *
     * @param competitionId 赛事主主键
     * @return 赛事主
     */
    @Override
    public Competition selectTCompetitionByCompetitionId(Long competitionId)
    {
        return competitionMapper.selectCompetitionByCompetitionId(competitionId);
    }

    /**
     * 查询赛事主列表
     *
     * @param tCompetition 赛事主
     * @return 赛事主
     */
    @Override
    public List<Competition> selectTCompetitionList(Competition tCompetition)
    {
        return competitionMapper.selectCompetitionList(tCompetition);
    }

    /**
     * 新增赛事主
     *
     * @param tCompetition 赛事主
     * @return 结果
     */
    @Override
    public int insertTCompetition(Competition tCompetition)
    {
        tCompetition.setCreateTime(DateUtils.getNowDate());
        return competitionMapper.insertCompetition(tCompetition);
    }

    /**
     * 修改赛事主
     *
     * @param tCompetition 赛事主
     * @return 结果
     */
    @Override
    public int updateTCompetition(Competition tCompetition)
    {
        return competitionMapper.updateCompetition(tCompetition);
    }

    /**
     * 批量删除赛事主
     *
     * @param competitionIds 需要删除的赛事主主键
     * @return 结果
     */
    @Override
    public int deleteTCompetitionByCompetitionIds(Long[] competitionIds)
    {
        return competitionMapper.deleteCompetitionByCompetitionIds(competitionIds);
    }

    /**
     * 删除赛事主信息
     *
     * @param competitionId 赛事主主键
     * @return 结果
     */
    @Override
    public int deleteTCompetitionByCompetitionId(Long competitionId)
    {
        return competitionMapper.deleteCompetitionByCompetitionId(competitionId);
    }

    @Override
    public Competition selectTCompetitionByCompetitionName(String competitionName) {
        return competitionMapper.selectTCompetitionByCompetitionName(competitionName);
    }
}
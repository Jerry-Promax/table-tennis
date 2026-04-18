package com.tts.mapper;

import com.tts.domain.entity.CompetitionSignup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@Mapper
public interface CompetitionSignupMapper {
    /**
     * 查询赛事报名记录
     *
     * @param signupId 赛事报名记录主键
     * @return 赛事报名记录
     */
    public CompetitionSignup selectCompetitionSignupBySignupId(Long signupId);

    /**
     * 查询赛事报名记录列表
     *
     * @param competitionSignup 赛事报名记录
     * @return 赛事报名记录集合
     */
    public List<CompetitionSignup> selectCompetitionSignupList(CompetitionSignup competitionSignup);

    /**
     * 新增赛事报名记录
     *
     * @param competitionSignup 赛事报名记录
     * @return 结果
     */
    public int insertCompetitionSignup(CompetitionSignup competitionSignup);

    /**
     * 修改赛事报名记录
     *
     * @param competitionSignup 赛事报名记录
     * @return 结果
     */
    public int updateCompetitionSignup(CompetitionSignup competitionSignup);

    /**
     * 删除赛事报名记录
     *
     * @param signupId 赛事报名记录主键
     * @return 结果
     */
    public int deleteCompetitionSignupBySignupId(Long signupId);

    /**
     * 批量删除赛事报名记录
     *
     * @param signupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompetitionSignupBySignupIds(Long[] signupIds);

    Integer selectCompetitionSignupExist(@Param("competitionId") Long competitionId, @Param("userId") Long userId);
}
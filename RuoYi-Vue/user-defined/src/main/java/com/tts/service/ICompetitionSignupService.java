package com.tts.service;

import com.tts.domain.entity.CompetitionSignup;
import com.tts.domain.vo.CompetitionSignupVo;
import com.tts.domain.vo.UserSignUpVo;

import java.util.List;

/**
 * 功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
public interface ICompetitionSignupService {

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
    public List<CompetitionSignupVo> selectCompetitionSignupList(CompetitionSignup competitionSignup);

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
     * 批量删除赛事报名记录
     *
     * @param signupIds 需要删除的赛事报名记录主键集合
     * @return 结果
     */
    public int deleteCompetitionSignupBySignupIds(Long[] signupIds);

    /**
     * 删除赛事报名记录信息
     *
     * @param signupId 赛事报名记录主键
     * @return 结果
     */
    public int deleteCompetitionSignupBySignupId(Long signupId);

    List<UserSignUpVo> selectUserGameSignupList(Long userId);
}

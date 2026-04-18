package com.tts.service.impl;

import com.tts.common.exception.ServiceException;
import com.tts.common.utils.DateUtils;
import com.tts.domain.entity.Competition;
import com.tts.domain.entity.CompetitionSignup;
import com.tts.domain.vo.CompetitionSignupVo;
import com.tts.domain.vo.UserSignUpVo;
import com.tts.mapper.CompetitionMapper;
import com.tts.mapper.CompetitionSignupMapper;
import com.tts.service.ICompetitionSignupService;
import com.tts.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@Service
public class CompetitionSignupServiceImpl implements ICompetitionSignupService {
    @Autowired
    private CompetitionSignupMapper competitionSignupMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private CompetitionMapper competitionMapper;

    /**
     * 查询赛事报名记录
     *
     * @param signupId 赛事报名记录主键
     * @return 赛事报名记录
     */
    @Override
    public CompetitionSignup selectCompetitionSignupBySignupId(Long signupId)
    {
        return competitionSignupMapper.selectCompetitionSignupBySignupId(signupId);
    }

    /**
     * 查询赛事报名记录列表
     *
     * @param competitionSignup 赛事报名记录
     * @return 赛事报名记录
     */
    @Override
    public List<CompetitionSignupVo> selectCompetitionSignupList(CompetitionSignup competitionSignup)
    {
        List<CompetitionSignup> competitionSignups = competitionSignupMapper.selectCompetitionSignupList(competitionSignup);
        List<CompetitionSignupVo> competitionSignupVos = competitionSignups.stream().map(Signup -> {
            CompetitionSignupVo competitionSignupVo = new CompetitionSignupVo();
            competitionSignupVo.setSignupId(Signup.getSignupId());
            competitionSignupVo.setCompetitionId(Signup.getCompetitionId());
            competitionSignupVo.setUserId(Signup.getUserId());
            competitionSignupVo.setSignupStatus(Signup.getSignupStatus());
            competitionSignupVo.setAuditBy(Signup.getAuditBy());
            competitionSignupVo.setAuditTime(Signup.getAuditTime());
            competitionSignupVo.setCompetitionName(competitionMapper.selectCompetitionByCompetitionId(Signup.getCompetitionId()).getCompetitionName());
            competitionSignupVo.setNickName(sysUserMapper.selectUserById(Signup.getUserId()).getNickName());
            competitionSignupVo.setCreateTime(Signup.getCreateTime());
            return competitionSignupVo;
        }).collect(Collectors.toList());
        return competitionSignupVos;
    }

    /**
     * 新增赛事报名记录
     *
     * @param competitionSignup 赛事报名记录
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCompetitionSignup(CompetitionSignup competitionSignup)
    {
        // 查询是否存在报名记录
        int count = competitionSignupMapper.selectCompetitionSignupExist(competitionSignup.getCompetitionId(), competitionSignup.getUserId());
        if (count > 0) {
            throw new ServiceException("您已报名过该赛事");
        }
        // 插入报名表，封装时间
        competitionSignup.setCreateTime(DateUtils.getNowDate());
        int i = competitionSignupMapper.insertCompetitionSignup(competitionSignup);
        // 更新赛事表人数 +1
        competitionMapper.updateSignupCount(competitionSignup.getCompetitionId(), 1);
        return i;
    }

    /**
     * 修改赛事报名记录
     *
     * @param competitionSignup 赛事报名记录
     * @return 结果
     */
    @Override
    public int updateCompetitionSignup(CompetitionSignup competitionSignup)
    {
        if (competitionSignup.getSignupStatus() == 1 || competitionSignup.getSignupStatus() == 2){
            competitionSignup.setAuditTime(DateUtils.getNowDate());
        }
        return competitionSignupMapper.updateCompetitionSignup(competitionSignup);
    }

    /**
     * 批量删除赛事报名记录
     *
     * @param signupIds 需要删除的赛事报名记录主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCompetitionSignupBySignupIds(Long[] signupIds)
    {
        // 循环查看是否有对应的报名记录
        for (Long signupId : signupIds) {
            CompetitionSignup signup = competitionSignupMapper.selectCompetitionSignupBySignupId(signupId);
            if (signup != null) {
                // 如果当前时间已经超过比赛结束时间，则不能退赛
                Competition competition = competitionMapper.selectCompetitionByCompetitionId(signup.getCompetitionId());
                if (DateUtils.getNowDate().after(competition.getEndTime())) {
                    throw new ServiceException("当前赛事已结束不能退赛");
                }
                // 扣减人数 -1
                competitionMapper.updateSignupCount(signup.getCompetitionId(), -1);
            }
        }
        return competitionSignupMapper.deleteCompetitionSignupBySignupIds(signupIds);
    }

    /**
     * 删除赛事报名记录信息
     *
     * @param signupId 赛事报名记录主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCompetitionSignupBySignupId(Long signupId)
    {
        // 查看是否有对应的报名记录
        CompetitionSignup signup = competitionSignupMapper.selectCompetitionSignupBySignupId(signupId);
        if (signup != null) {
            // 如果当前时间已经超过比赛结束时间，则不能退赛
            Competition competition = competitionMapper.selectCompetitionByCompetitionId(signup.getCompetitionId());
            if (DateUtils.getNowDate().after(competition.getEndTime())) {
                throw new ServiceException("当前赛事已结束不能退赛");
            }
            // 扣减人数 -1
            competitionMapper.updateSignupCount(signup.getCompetitionId(), -1);
        }
        return competitionSignupMapper.deleteCompetitionSignupBySignupId(signupId);
    }

    // todo 后续再优雅
    @Override
    public List<UserSignUpVo> selectUserGameSignupList(Long userId) {
        List<UserSignUpVo> userSignUpVos = new ArrayList<>();
        // 先根据userId查找到赛事报名记录
        CompetitionSignup competitionSignup = new CompetitionSignup();
        competitionSignup.setUserId(userId);
        List<CompetitionSignup> competitionSignups = competitionSignupMapper.selectCompetitionSignupList(competitionSignup);
        // 根据赛事记录的赛事ID查到赛事信息并过滤掉赛事状态不为1和2的赛事
        if (competitionSignups != null && !competitionSignups.isEmpty()) {
            for (CompetitionSignup c : competitionSignups) {
                // 查找到赛事信息，为了获取赛事状态和赛事名称
                Competition competition = competitionMapper.selectCompetitionByCompetitionId(c.getCompetitionId());
                if (competition != null && (competition.getStatus() == 1 || competition.getStatus() == 2)){
                    UserSignUpVo userSignUpVo = new UserSignUpVo();
                    userSignUpVo.setSignupId(c.getSignupId());
                    userSignUpVo.setCompetitionId(c.getCompetitionId());
                    userSignUpVo.setCompetitionName(competition.getCompetitionName());
                    userSignUpVo.setSignupStart(c.getCreateTime());
                    userSignUpVo.setStatus(competition.getStatus());
                    userSignUpVos.add(userSignUpVo);
                }
            }
        }
        return userSignUpVos;
    }


}
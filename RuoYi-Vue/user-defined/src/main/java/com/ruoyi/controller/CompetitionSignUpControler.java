package com.ruoyi.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.domain.dto.CompetitionSignupDto;
import com.ruoyi.domain.entity.CompetitionSignup;
import com.ruoyi.domain.vo.CompetitionSignupVo;
import com.ruoyi.service.ICompetitionService;
import com.ruoyi.service.ICompetitionSignupService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ruoyi.common.annotation.Log;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@RestController
@RequestMapping("/system/signup")
public class CompetitionSignUpControler extends BaseController {
    @Autowired
    private ICompetitionSignupService competitionSignupService;

    @Autowired
    private ICompetitionService competitionService;
    @Autowired
    private ISysUserService userService;
    /**
     * 查询赛事报名记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:signup:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompetitionSignupDto competitionSignupDto)
    {
        startPage();
        CompetitionSignup competitionSignup = new CompetitionSignup();
        BeanUtils.copyProperties(competitionSignupDto, competitionSignup);
        if (competitionSignupDto.getCompetitionName() != null){
            Long competitionId = competitionService.selectTCompetitionByCompetitionName(competitionSignupDto.getCompetitionName()).getCompetitionId();
            competitionSignup.setCompetitionId(competitionId);
        }
        if (competitionSignupDto.getNickName() != null) {
            Long userId = userService.selectUserByNickName(competitionSignupDto.getNickName()).getUserId();
            competitionSignup.setUserId(userId);
        }
        List<CompetitionSignupVo> list = competitionSignupService.selectCompetitionSignupList(competitionSignup);
        return getDataTable(list);
    }

    /**
     * 导出赛事报名记录列表
     */
//    @PreAuthorize("@ss.hasPermi('system:signup:export')")
//    @Log(title = "赛事报名记录", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, CompetitionSignup competitionSignup)
//    {
//        List<CompetitionSignup> list = competitionSignupService.selectCompetitionSignupList(competitionSignup);
//        ExcelUtil<CompetitionSignup> util = new ExcelUtil<CompetitionSignup>(CompetitionSignup.class);
//        util.exportExcel(response, list, "赛事报名记录数据");
//    }

    /**
     * 获取赛事报名记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:signup:query')")
    @GetMapping(value = "/{signupId}")
    public AjaxResult getInfo(@PathVariable("signupId") Long signupId)
    {
        return success(competitionSignupService.selectCompetitionSignupBySignupId(signupId));
    }

    /**
     * 新增赛事报名记录
     */
    @PreAuthorize("@ss.hasPermi('system:signup:add')")
    @Log(title = "赛事报名记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompetitionSignup competitionSignup)
    {
        return toAjax(competitionSignupService.insertCompetitionSignup(competitionSignup));
    }

    /**
     * 修改赛事报名记录
     */
    @PreAuthorize("@ss.hasPermi('system:signup:edit')")
    @Log(title = "赛事报名记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompetitionSignup competitionSignup)
    {
        return toAjax(competitionSignupService.updateCompetitionSignup(competitionSignup));
    }

    /**
     * 删除赛事报名记录
     */
    @PreAuthorize("@ss.hasPermi('system:signup:remove')")
    @Log(title = "赛事报名记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{signupIds}")
    public AjaxResult remove(@PathVariable Long[] signupIds)
    {
        return toAjax(competitionSignupService.deleteCompetitionSignupBySignupIds(signupIds));
    }
    @PreAuthorize("@ss.hasPermi('system:signup:list')")
    @GetMapping("/userGameList/{userId}")
    public TableDataInfo userGameList(@PathVariable("userId") Long userId){
        startPage();
//        CompetitionSignup competitionSignup = new CompetitionSignup();
//        BeanUtils.copyProperties(competitionSignupDto, competitionSignup);
        return getDataTable(competitionSignupService.selectUserGameSignupList(userId));
    }
}
package com.ruoyi.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.entity.Competition;
import com.ruoyi.service.ICompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-27
 */
@RestController
@RequestMapping("/system/competition")
public class CompetitionController extends BaseController {
    @Autowired
    private ICompetitionService CompetitionService;

    /**
     * 查询赛事主列表
     */
    @PreAuthorize("@ss.hasPermi('system:competition:list')")
    @GetMapping("/list")
    public TableDataInfo list(Competition tCompetition)
    {
        startPage();
        List<Competition> list = CompetitionService.selectTCompetitionList(tCompetition);
        return getDataTable(list);
    }

    /**
     * 导出赛事主列表
     */
    @PreAuthorize("@ss.hasPermi('system:competition:export')")
    @Log(title = "赛事主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Competition tCompetition)
    {
        List<Competition> list = CompetitionService.selectTCompetitionList(tCompetition);
        ExcelUtil<Competition> util = new ExcelUtil<Competition>(Competition.class);
        util.exportExcel(response, list, "赛事主数据");
    }

    /**
     * 获取赛事主详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:competition:query')")
    @GetMapping(value = "/{competitionId}")
    public AjaxResult getInfo(@PathVariable("competitionId") Long competitionId)
    {
        return success(CompetitionService.selectTCompetitionByCompetitionId(competitionId));
    }

    /**
     * 新增赛事主
     */
    @PreAuthorize("@ss.hasPermi('system:competition:add')")
    @Log(title = "赛事主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Competition tCompetition)
    {
        return toAjax(CompetitionService.insertTCompetition(tCompetition));
    }

    /**
     * 修改赛事主
     */
    @PreAuthorize("@ss.hasPermi('system:competition:edit')")
    @Log(title = "赛事主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Competition tCompetition)
    {
        return toAjax(CompetitionService.updateTCompetition(tCompetition));
    }

    /**
     * 删除赛事主
     */
    @PreAuthorize("@ss.hasPermi('system:competition:remove')")
    @Log(title = "赛事主", businessType = BusinessType.DELETE)
    @DeleteMapping("/{competitionIds}")
    public AjaxResult remove(@PathVariable Long[] competitionIds)
    {
        return toAjax(CompetitionService.deleteTCompetitionByCompetitionIds(competitionIds));
    }
}
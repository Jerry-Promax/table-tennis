package com.tts.controller;

import com.tts.common.annotation.Log;
import com.tts.common.core.controller.BaseController;
import com.tts.common.core.domain.AjaxResult;
import com.tts.common.core.page.TableDataInfo;
import com.tts.common.enums.BusinessType;
import com.tts.common.utils.poi.ExcelUtil;
import com.tts.domain.entity.BallTable;
import com.tts.domain.entity.BallTablePrice;
import com.tts.service.IBallTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-21
 */
@RestController
@RequestMapping("/system/table")
public class BallTableController extends BaseController {
    @Autowired
    private IBallTableService ballTableService;

    /**
     * 查询乒乓球台基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:table:list')")
    @GetMapping("/list")
    public TableDataInfo list(BallTable ballTable)
    {
        startPage();
        List<BallTable> list = ballTableService.selectBallTableList(ballTable);
        return getDataTable(list);
    }

    /**
     * 导出乒乓球台基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:table:export')")
    @Log(title = "乒乓球台基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BallTable ballTable)
    {
        List<BallTable> list = ballTableService.selectBallTableList(ballTable);
        ExcelUtil<BallTable> util = new ExcelUtil<BallTable>(BallTable.class);
        util.exportExcel(response, list, "乒乓球台基础信息数据");
    }

    /**
     * 获取乒乓球台基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:table:query')")
    @GetMapping(value = "/{tableId}")
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId)
    {
        return success(ballTableService.selectBallTableByTableId(tableId));
    }

    /**
     * 新增乒乓球台基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:table:add')")
    @Log(title = "乒乓球台基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BallTable ballTable)
    {
        return toAjax(ballTableService.insertBallTable(ballTable));
    }

    /**
     * 修改乒乓球台基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:table:edit')")
    @Log(title = "乒乓球台基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BallTable ballTable)
    {
        return toAjax(ballTableService.updateBallTable(ballTable));
    }

    /**
     * 删除乒乓球台基础信息
     */
    @PreAuthorize("@ss.hasPermi('system:table:remove')")
    @Log(title = "乒乓球台基础信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public AjaxResult remove(@PathVariable Long[] tableIds)
    {
        return toAjax(ballTableService.deleteBallTableByTableIds(tableIds));
    }
    /**
     * 获取乒乓球台价格信息
     */
    @GetMapping("/getTablePrice/{tableId}")
    public AjaxResult getTablePrice(@PathVariable Long tableId)
    {
        return success(ballTableService.selectTablePriceByTableId(tableId));
    }
    /**
     * 修改乒乓球台价格信息
     */
    @PutMapping("/updateTablePrice")
    public AjaxResult updateTablePrice(@RequestBody BallTablePrice ballTablePrice)
    {
        return toAjax(ballTableService.updateTablePrice(ballTablePrice));
    }
}
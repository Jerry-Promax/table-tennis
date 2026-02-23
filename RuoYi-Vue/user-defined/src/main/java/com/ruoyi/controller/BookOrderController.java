package com.ruoyi.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.domain.entity.BookOrder;
import com.ruoyi.domain.vo.BookOrderVo;
import com.ruoyi.service.IBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 类功能描述
 * <p>
 * 作者：jerry
 * 日期：2026-01-30
 */
@RestController
@RequestMapping("/system/order")
public class BookOrderController extends BaseController {
    @Autowired
    private IBookOrderService bookOrderService;

    /**
     * 查询预约订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(BookOrder bookOrder)
    {
        startPage();
        List<BookOrderVo> list = bookOrderService.selectBookOrderList(bookOrder);
        return getDataTable(list);
    }

    /**
     * 导出预约订单列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "预约订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookOrder bookOrder)
    {
        List<BookOrderVo> list = bookOrderService.selectBookOrderList(bookOrder);
        ExcelUtil<BookOrderVo> util = new ExcelUtil<BookOrderVo>(BookOrderVo.class);
        util.exportExcel(response, list, "预约订单数据");
    }

    /**
     * 获取预约订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(bookOrderService.selectBookOrderByOrderId(orderId));
    }

    /**
     * 新增预约订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "预约订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookOrder bookOrder)
    {
        int rows = bookOrderService.insertBookOrder(bookOrder);
        if (rows > 0) {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("orderId", bookOrder.getOrderNo());
            return ajax;
        }
        return error();
    }

    /**
     * 修改预约订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "预约订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookOrder bookOrder)
    {
        return toAjax(bookOrderService.updateBookOrder(bookOrder));
    }

    /**
     * 删除预约订单
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "预约订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(bookOrderService.deleteBookOrderByOrderIds(orderIds));
    }
}
package com.sli.controller;

import com.sli.api.system.domain.SysOperLog;
import com.sli.common.core.domain.AjaxResult;
import com.sli.common.core.web.controller.BaseController;
import com.sli.common.core.web.page.TableDataInfo;
import com.sli.log.annotation.Log;
import com.sli.log.enums.BusinessType;
import com.sli.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 操作日志记录
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/operlog")
public class SysOperlogController extends BaseController
{
    @Autowired
    private ISysOperLogService operLogService;

    @PreAuthorize("@ss.hasPermi('system:operlog:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOperLog operLog)
    {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:operlog:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOperLog operLog) throws IOException
    {
//        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
//        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
//        util.exportExcel(response, list, "操作日志");
    }

    @PreAuthorize("@ss.hasPermi('system:operlog:remove')")
    @DeleteMapping("/{operIds}")
    public AjaxResult remove(@PathVariable Long[] operIds)
    {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    @PreAuthorize("@ss.hasPermi('system:operlog:remove')")
    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean()
    {
        operLogService.cleanOperLog();
        return AjaxResult.success();
    }

    @PostMapping
    public AjaxResult add(@RequestBody SysOperLog operLog)
    {
        return toAjax(operLogService.insertOperlog(operLog));
    }
}

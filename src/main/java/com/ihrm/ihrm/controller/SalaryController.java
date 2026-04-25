package com.ihrm.ihrm.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ihrm.ihrm.VO.SalaryMonthExcel;
import com.ihrm.ihrm.VO.SalaryReportVO;
import com.ihrm.ihrm.VO.SalaryUserVO;
import com.ihrm.ihrm.dto.SalaryInitDTO;
import com.ihrm.ihrm.dto.SalaryModifyDTO;
import com.ihrm.ihrm.entity.SalaryCompanySetting;
import com.ihrm.ihrm.entity.SalaryMonthReport;
import com.ihrm.ihrm.entity.SalarySettings;
import com.ihrm.ihrm.service.SalaryCompanySettingService;
import com.ihrm.ihrm.service.SalaryMonthReportService;
import com.ihrm.ihrm.service.SalarySettingsService;
import com.ihrm.ihrm.util.R;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Resource;

/**
 * 薪资管理模块控制器
 * 包含：工资设置、津贴设置、工资报表
 */
@RestController
@RequestMapping("/salarys")
public class SalaryController {

    @Resource
    private SalaryCompanySettingService salaryCompanySettingService;

    @Resource
    private SalarySettingsService salarySettingsService;

    @Resource
    private SalaryMonthReportService salaryMonthReportService;

    // ======================== 1. 获取企业是否设置当月工资 ========================
    @GetMapping("/company-settings")
    public R getCompanySalarySetting(@RequestHeader(value = "Authorization", required = false) String token) {
        LambdaQueryWrapper<SalaryCompanySetting> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SalaryCompanySetting::getCompanyId, "1");
        SalaryCompanySetting setting = salaryCompanySettingService.getOne(wrapper);

        if (setting == null) {
            setting = new SalaryCompanySetting();
            setting.setCompanyId("1");
            setting.setIsSettings(0);
            setting.setDataMonth("202504");
        }
        return R.success(setting);
    }

    // ======================== 2. 保存企业工资设置 ========================
    @PostMapping("/company-settings")
    public R saveCompanySalarySetting(
            @RequestBody SalaryCompanySetting setting,
            @RequestHeader(value = "Authorization", required = false) String token) {

        LambdaQueryWrapper<SalaryCompanySetting> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SalaryCompanySetting::getCompanyId, setting.getCompanyId());
        wrapper.eq(SalaryCompanySetting::getDataMonth, setting.getDataMonth());

        SalaryCompanySetting one = salaryCompanySettingService.getOne(wrapper);
        if (one != null) {
            setting.setId(one.getId());
            salaryCompanySettingService.updateById(setting);
        } else {
            salaryCompanySettingService.save(setting);
        }
        return R.success(null);
    }

    // ======================== 3. 获取企业计薪及津贴设置 ========================
    @GetMapping("/settings")
    public R getSalarySettings(@RequestHeader(value = "Authorization", required = false) String token) {
        LambdaQueryWrapper<SalarySettings> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SalarySettings::getCompanyId, "1");
        SalarySettings settings = salarySettingsService.getOne(wrapper);
        return R.success(settings);
    }

    // ======================== 4. 保存企业计薪及津贴设置 ========================
    @PostMapping("/settings")
    public R saveSalarySettings(
            @RequestBody SalarySettings settings,
            @RequestHeader(value = "Authorization", required = false) String token) {

        settings.setCompanyId("1");
        LambdaQueryWrapper<SalarySettings> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SalarySettings::getCompanyId, "1");

        SalarySettings one = salarySettingsService.getOne(wrapper);
        if (one != null) {
            settings.setId(one.getId());
            salarySettingsService.updateById(settings);
        } else {
            salarySettingsService.save(settings);
        }
        return R.success(null);
    }

    // ======================== 5. 构造新月份工资报表 ========================
    @PutMapping("/reports/{yearMonth}/newReport")
    public R newSalaryReport(
            @PathVariable String yearMonth,
            @RequestHeader(value = "Authorization", required = false) String token) {
        // TODO: 生成新月份工资报表
        // 示例: salaryMonthReportService.generateNewReport(yearMonth);
        return R.success(null);
    }

    // ======================== 6. 根据年月查询企业用户薪资列表 ========================
    @GetMapping("/reports/{yearMonth}")
    public R getSalaryReportList(
            @PathVariable String yearMonth,
            @RequestParam(required = false) String opType,
            @RequestHeader(value = "Authorization", required = false) String token) {

        List<SalaryReportVO> list = new ArrayList<>();
        // TODO: 从数据库查询企业用户薪资列表
        // 示例: list = salaryMonthReportService.getSalaryReportList(yearMonth, opType);

        return R.success(list);
    }

    // ======================== 7. 查询用户薪资 ========================
    @GetMapping("/modify/{userId}")
    public R getUserSalary(
            @PathVariable String userId,
            @RequestHeader(value = "Authorization", required = false) String token) {

        SalaryUserVO vo = new SalaryUserVO();
        // TODO: 从数据库查询用户薪资
        // 示例: vo = salarySettingsService.getUserSalary(userId);

        return R.success(vo);
    }

    // ======================== 8. 调薪 ========================
    @PostMapping("/modify/{userId}")
    public R modifySalary(
            @PathVariable String userId,
            @RequestBody SalaryModifyDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 保存调薪记录、更新用户工资表
        // TODO: 实现调薪逻辑
        // 示例: salarySettingsService.modifySalary(userId, dto);
        return R.success(null);
    }

    // ======================== 9. 员工定薪（首次设置薪资） ========================
    @PostMapping("/init/{userId}")
    public R initSalary(
            @PathVariable String userId,
            @RequestBody SalaryInitDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 保存员工初始薪资、转正薪资
        // TODO: 实现员工定薪逻辑
        // 示例: salarySettingsService.initSalary(userId, dto);
        return R.success(null);
    }

    // ======================== 10. 批量导出工资月份报表 ========================
    @GetMapping("/historys/{yearMonth}/export")
    public void exportSalaryReport(
            @PathVariable String yearMonth,
            @RequestParam(required = false) String fileName,
            @RequestHeader("Authorization") String token,
            HttpServletResponse response) throws Exception {

        // 1. 文件名处理
        if (fileName == null || fileName.isEmpty()) {
            fileName = "工资报表" + yearMonth;
        }
        String exportFileName = URLEncoder.encode(fileName, "UTF-8") + ".xlsx";

        // 2. 设置响应头（下载文件）
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=UTF-8''" + exportFileName);

        // 3. 查询当月工资数据
        List<SalaryMonthExcel> list = new ArrayList<>();
        // TODO: 从数据库查询工资数据
        // 示例: list = salaryMonthReportService.getSalaryDataForExport(yearMonth);

        // 4. 写出Excel
        EasyExcel.write(response.getOutputStream(), SalaryMonthExcel.class)
                .sheet("工资报表")
                .doWrite(list);
    }
}
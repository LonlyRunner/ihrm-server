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
        // 模拟生成工资成功
        return R.success(null);
    }

    // ======================== 6. 根据年月查询企业用户薪资列表 ========================
    @GetMapping("/reports/{yearMonth}")
    public R getSalaryReportList(
            @PathVariable String yearMonth,
            @RequestParam(required = false) String opType,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // ======================
        // 直接返回【前端示例格式】模拟数据
        // 1:1 匹配你给的 OpenAPI 样例
        // ======================

        List<SalaryReportVO> list = new ArrayList<>();

        // ========== 模拟1条数据（你可以循环生成多条）==========
        SalaryReportVO vo = new SalaryReportVO();
        vo.setId(null);
        vo.setArchiveId(null);
        vo.setUserId("1235396724497268736");
        vo.setUsername("吕勇锐");
        vo.setMobile("13600000001");
        vo.setWorkNumber("0001");
        vo.setDepartmentName("总裁办");
        vo.setIdNumber(null);
        vo.setInServiceStatus("1");
        vo.setFormOfEmployment(null);
        vo.setBankCardNumber(null);
        vo.setOpeningBank(null);
        vo.setProvidentFundIndividual(0);
        vo.setSocialSecurityIndividual(0);
        vo.setOldAgeIndividual(null);
        vo.setMedicalIndividual(null);
        vo.setUnemployedIndividual(null);
        vo.setSocialSecurity(null);
        vo.setTotalProvidentFundIndividual(null);
        vo.setSocialSecurityEnterprise(0);
        vo.setPensionEnterprise(null);
        vo.setMedicalEnterprise(null);
        vo.setUnemployedEnterprise(null);
        vo.setIndustrialInjuryEnterprise(null);
        vo.setChildbearingEnterprise(null);
        vo.setBigDiseaseEnterprise(null);
        vo.setProvidentFundEnterprises(0);
        vo.setSocialSecurityProvidentFundEnterprises(0);
        vo.setTaxToProvidentFund(null);
        vo.setOfficialSalaryDays(new BigDecimal("21.75"));
        vo.setAttendanceDeductionMonthly("0.0000");
        vo.setSalaryStandard(null);
        vo.setCurrentSalaryTotalBase(new BigDecimal("5546.25"));
        vo.setCurrentBaseSalary(0);
        vo.setBaseSalaryByMonth(0);
        vo.setTaxCountingMethod(null);
        vo.setBaseSalaryToTaxByMonth(null);
        vo.setSalaryBeforeTax(0);
        vo.setSalary(null);
        vo.setSalaryByTax(new BigDecimal("5546.25"));
        vo.setPaymentBeforeTax(null);
        vo.setTax(new BigDecimal("449.625"));
        vo.setSalaryAfterTax(null);
        vo.setPayment(new BigDecimal("0"));
        vo.setPaymentRemark(null);
        vo.setSalaryCost(null);
        vo.setEnterpriseLaborCost(null);
        vo.setSalaryChangeAmount(new BigDecimal("5546.25"));
        vo.setSalaryChangeScale(null);
        vo.setEffectiveTimeOfPayAdjustment(null);
        vo.setCauseOfSalaryAdjustment(null);
        vo.setRemark(null);
        vo.setPaymentMonths(null);
        vo.setEntTotal(0);
        vo.setPerTotal(0);
        vo.setApersonOfGreatDisease(null);

        list.add(vo);
        // ========== 模拟结束 ==========

        return R.success(list);
    }

    // ======================== 7. 查询用户薪资 ========================
    @GetMapping("/modify/{userId}")
    public R getUserSalary(
            @PathVariable String userId,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 模拟返回数据（完全匹配你的示例）
        SalaryUserVO vo = new SalaryUserVO();
        vo.setUserId(userId);
        vo.setCurrentBasicSalary(8888);
        vo.setCurrentPostWage(8888);
        vo.setFixedBasicSalary(8888);
        vo.setFixedPostWage(8888);
        vo.setCorrectionOfBasicWages(1234);
        vo.setTurnToPostWages(1234);

        return R.success(vo);
    }

    // ======================== 8. 调薪 ========================
    @PostMapping("/modify/{userId}")
    public R modifySalary(
            @PathVariable String userId,
            @RequestBody SalaryModifyDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 这里可以写业务：保存调薪记录、更新用户工资表
        // 模拟调薪成功
        return R.success(null);
    }

    // ======================== 9. 员工定薪（首次设置薪资） ========================
    @PostMapping("/init/{userId}")
    public R initSalary(
            @PathVariable String userId,
            @RequestBody SalaryInitDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 业务：保存员工初始薪资、转正薪资
        // 模拟成功
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

        // 3. 模拟查询当月工资数据（你可以替换成真实DB查询）
        List<SalaryMonthExcel> list = new ArrayList<>();
        SalaryMonthExcel excel = new SalaryMonthExcel();
        excel.setUserId("1235396724497268736");
        excel.setUsername("吕勇锐");
        excel.setMobile("13600000001");
        excel.setWorkNumber("0001");
        excel.setDepartmentName("总裁办");
        excel.setInServiceStatus("在职");
        excel.setOfficialSalaryDays(new BigDecimal("21.75"));
        excel.setAttendanceDeductionMonthly("0");
        excel.setCurrentSalaryTotalBase(new BigDecimal("5546.25"));
        excel.setCurrentBaseSalary(0);
        excel.setSalaryByTax(new BigDecimal("5546.25"));
        excel.setTax(new BigDecimal("449.625"));
        excel.setPayment(new BigDecimal("0"));
        list.add(excel);

        // 4. 写出Excel
        EasyExcel.write(response.getOutputStream(), SalaryMonthExcel.class)
                .sheet("工资报表")
                .doWrite(list);
    }
}
package com.ihrm.ihrm.VO;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryMonthExcel {

    @ExcelProperty("用户ID")
    private String userId;

    @ExcelProperty("姓名")
    private String username;

    @ExcelProperty("手机号")
    private String mobile;

    @ExcelProperty("工号")
    private String workNumber;

    @ExcelProperty("部门")
    private String departmentName;

    @ExcelProperty("在职状态")
    private String inServiceStatus;

    @ExcelProperty("计薪天数")
    private BigDecimal officialSalaryDays;

    @ExcelProperty("考勤扣款")
    private String attendanceDeductionMonthly;

    @ExcelProperty("工资基数")
    private BigDecimal currentSalaryTotalBase;

    @ExcelProperty("基本工资")
    private Integer currentBaseSalary;

    @ExcelProperty("应纳税工资")
    private BigDecimal salaryByTax;

    @ExcelProperty("应扣个税")
    private BigDecimal tax;

    @ExcelProperty("实发工资")
    private BigDecimal payment;
}
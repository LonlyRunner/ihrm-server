package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("salary_month_report")
public class SalaryMonthReport extends BaseEntity {

    private String companyId;

    private String userId;

    private String username;

    private String departmentId;

    private String departmentName;

    /**
     * 年月
     */
    private String yearMonthVal;

    /**
     * 基本工资
     */
    private Integer baseSalary;

    /**
     * 岗位工资
     */
    private Integer workSalary;

    /**
     * 绩效工资
     */
    private Integer performanceSalary;

    /**
     * 交通补贴
     */
    private Integer transportationSubsidy;

    /**
     * 通讯补贴
     */
    private Integer communicationSubsidy;

    /**
     * 午餐补贴
     */
    private Integer lunchAllowance;

    /**
     * 住房补贴
     */
    private Integer housingSubsidy;

    /**
     * 缺勤扣款
     */
    private Integer absenceDeduct;

    /**
     * 迟到扣款
     */
    private Integer lateDeduct;

    /**
     * 社保个人部分
     */
    private Integer socialSecuritySelf;

    /**
     * 公积金个人部分
     */
    private Integer housingFundSelf;

    /**
     * 个税
     */
    private Integer tax;

    /**
     * 应发工资
     */
    private Integer shouldPay;

    /**
     * 实发工资
     */
    private Integer realPay;
}
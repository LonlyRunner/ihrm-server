package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("salary_settings")
public class SalarySettings extends BaseEntity {

    private String companyId;

    /**
     * 社保自然月
     */
    private Integer socialSecurityType;

    /**
     * 津贴方案名称
     */
    private String subsidyName;

    /**
     * 津贴备注
     */
    private String subsidyRemark;

    /**
     * 交通补贴计算类型
     */
    private Integer transportationSubsidyScheme;

    /**
     * 交通补贴金额
     */
    private Integer transportationSubsidyAmount;

    /**
     * 通讯补贴计算类型
     */
    private Integer communicationSubsidyScheme;

    /**
     * 通讯补贴金额
     */
    private Integer communicationSubsidyAmount;

    /**
     * 午餐补贴计算类型
     */
    private Integer lunchAllowanceScheme;

    /**
     * 午餐补贴金额
     */
    private Integer lunchAllowanceAmount;

    /**
     * 住房补助计算类型
     */
    private Integer housingSubsidyScheme;

    /**
     * 住房补助金额
     */
    private Integer housingSubsidyAmount;

    /**
     * 计税方式
     */
    private Integer taxCalculationType;
}
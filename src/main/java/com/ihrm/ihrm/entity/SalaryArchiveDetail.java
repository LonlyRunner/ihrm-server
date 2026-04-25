package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("salary_archive_detail")
public class SalaryArchiveDetail extends BaseEntity {

    private String salaryArchiveId;

    private String companyId;

    private String employeeId;

    private String employeeName;

    private String departmentId;

    private String departmentName;

    private String postId;

    private String postName;

    private String rankId;

    private String rankName;

    private String entryDate;

    private Integer socialSecurityType;

    private Integer socialSecurityBase;

    private Integer accumulationFundBase;

    private Integer taxCalculationType;

    private Integer salaryMonth;

    private Integer actualWorkDays;

    private Integer basicSalary;

    private Integer performanceSalary;

    private Integer postSalary;

    private Integer senioritySalary;

    private Integer trafficAllowance;

    private Integer communicationAllowance;

    private Integer lunchAllowance;

    private Integer housingAllowance;

    private Integer otherAllowance;

    private Integer totalSalary;

    private Integer socialSecurityPersonal;

    private Integer socialSecurityCompany;

    private Integer accumulationFundPersonal;

    private Integer accumulationFundCompany;

    private Integer personalIncomeTax;

    private Integer actualSalary;

    private Integer isSend;

    private String sendTime;

    private String sendBatchId;
}
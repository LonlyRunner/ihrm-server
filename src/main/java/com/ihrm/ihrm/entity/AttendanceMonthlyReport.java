package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("atte_monthly_report")
public class AttendanceMonthlyReport {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String userId;
    private String atteArchiveMonthlyId;
    private String name;
    private String workNumber;
    private String mobile;
    private String atteSolution;
    private String department;
    private String workCity;
    private String yearLeaveDays;
    private String leaveDays;
    private String sickLeaveDays;
    private String longSickLeaveDays;
    private String marraiageLeaveDays;
    private String funeralLeaveDays;
    private String maternityLeaveDays;
    private String rewardMaternityLeaveDays;
    private String paternityLeaveDays;
    private String homeLeavaDays;
    private String accidentialLeaveDays;
    private String dayOffLeaveDays;
    private String doctorOffLeaveDays;
    private String abortionLeaveDays;
    private String normalDays;
    private String outgoingDays;
    private String onBusinessDays;
    private String laterTimes;
    private String earlyTimes;
    private String signedTimes;
    private String hoursPerDays;
    private String hoursPerWorkDay;
    private String hoursPerRestDay;
    private String clockRate;
    private String absenceDays;
    private Integer isFullAttendanceint;
    private String actualAtteUnofficialDays;
    private String actualAtteOfficialDays;
    private String workingDays;
    private String salaryStandards;
}
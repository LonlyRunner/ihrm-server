package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("deduction_setting")
public class DeductionSetting {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String dedTypeCode;
    private String isEnable;
    private String departmentId;
    private String periodLowerLimit;
    private String periodUpperLimit;
    private String timesLowerLimit;
    private String timesUpperLimit;
    private String dedAmonutLowerLimit;
    private String dedAmonutUpperLimit;
    private String absenceDays;
    private String fineSalaryMultiples;
    private String absenceTimesUpperLimt;
}
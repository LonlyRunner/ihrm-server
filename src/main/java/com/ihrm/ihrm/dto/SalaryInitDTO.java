package com.ihrm.ihrm.dto;

import lombok.Data;

@Data
public class SalaryInitDTO {
    private String userId;
    private String currentBasicSalary;    // 当前基本工资
    private String currentPostWage;       // 当前岗位工资
    private String correctionOfBasicWages;// 转正基本工资
    private String turnToPostWages;       // 转正岗位工资
}
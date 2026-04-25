package com.ihrm.ihrm.dto;

import lombok.Data;

@Data
public class SalaryModifyDTO {
    private String userId;
    private String currentBasicSalary;
    private String currentPostWage;
}
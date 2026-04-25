package com.ihrm.ihrm.VO;

import lombok.Data;

@Data
public class SalaryUserVO {
    private String userId;
    private Integer currentBasicSalary;
    private Integer currentPostWage;
    private Integer fixedBasicSalary;
    private Integer fixedPostWage;
    private Integer correctionOfBasicWages;
    private Integer turnToPostWages;
}
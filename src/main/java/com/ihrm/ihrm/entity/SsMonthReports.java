package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("ss_month_reports")
public class SsMonthReports {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String companyId;

    private String yearsMonth;

    private String departmentId;

    private String departmentName;

    private Integer totalPeople;

    private BigDecimal enterprisePayment;

    private BigDecimal personalPayment;

    private BigDecimal total;

    private LocalDateTime createTime;
}
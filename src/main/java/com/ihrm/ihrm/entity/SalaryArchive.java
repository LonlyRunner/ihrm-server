package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("salary_archive")
public class SalaryArchive extends BaseEntity {

    private String companyId;

    private String archiveBatchId;

    private String archiveYear;

    private String archiveMonth;

    private Integer totalEmployee;

    private Integer totalSalary;

    private Integer totalTax;

    private Integer totalSocialSecurity;

    private Integer totalAccumulationFund;

    private Integer isArchive;

    private String archiveTime;

    private Integer isSend;

    private String sendTime;

    private String sendBatchId;
}
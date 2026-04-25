package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("ss_archives")
public class SsArchives {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String companyId;

    private String yearsMonth;

    private LocalDateTime creationTime;

    private BigDecimal enterprisePayment;

    private BigDecimal personalPayment;

    private BigDecimal total;
}
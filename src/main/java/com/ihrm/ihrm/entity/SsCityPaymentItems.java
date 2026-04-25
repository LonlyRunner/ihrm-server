package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("ss_city_payment_items")
public class SsCityPaymentItems {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String cityId;

    private String paymentItemName;

    private String paymentItemType;

    private String socialSecurityType;

    private BigDecimal enterpriseBase;

    private BigDecimal enterpriseProportion;

    private BigDecimal personalBase;

    private BigDecimal personalProportion;

    private Integer isEnabled;

    private LocalDateTime createTime;
}
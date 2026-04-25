package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("ss_user_social_security")
public class SsUserSocialSecurity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    private String companyId;

    private String departmentId;

    private Integer enterprisesPaySocialSecurityThisMonth;

    private Integer enterprisesPayTheProvidentFundThisMonth;

    private String participatingInTheCityId;

    private Integer socialSecurityType;

    private Integer householdRegistrationType;

    private Integer socialSecurityBase;

    private BigDecimal industrialInjuryRatio;

    private String socialSecurityNotes;

    private String providentFundCityId;

    private Integer providentFundBase;

    private BigDecimal enterpriseProportion;

    private BigDecimal personalProportion;

    private String enterpriseProvidentFundPayment;

    private String personalProvidentFundPayment;

    private String providentFundNotes;

    private LocalDateTime lastModifyTime;

    private LocalDateTime socialSecuritySwitchUpdateTime;

    private LocalDateTime providentFundSwitchUpdateTime;

    private String householdRegistration;

    private String participatingInTheCity;

    private String providentFundCity;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
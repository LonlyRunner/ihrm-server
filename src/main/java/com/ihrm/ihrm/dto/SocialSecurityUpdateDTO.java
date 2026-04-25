package com.ihrm.ihrm.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SocialSecurityUpdateDTO {
    private String userId;
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
    private String lastModifyTime;
    private String socialSecuritySwitchUpdateTime;
    private String providentFundSwitchUpdateTime;
    private String householdRegistration;
    private String participatingInTheCity;
    private String providentFundCity;
}
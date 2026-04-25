package com.ihrm.ihrm.VO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UserSocialSecurityVO {
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
    private Integer enterpriseProvidentFundPayment;
    private Integer personalProvidentFundPayment;
    private String providentFundNotes;
    private String lastModifyTime;
    private String socialSecuritySwitchUpdateTime;
    private String providentFundSwitchUpdateTime;
    private String householdRegistration;
    private String participatingInTheCity;
    private String providentFundCity;
}
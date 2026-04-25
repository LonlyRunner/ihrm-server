package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("ss_user_social_security")
public class SocialSecurity extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String userId;

    private String username;

    private String mobile;

    @TableField("participating_in_the_city_id")
    private String participatingInTheCityId;

    @TableField("participating_in_the_city")
    private String participatingInTheCity;

    @TableField("social_security_type")
    private Integer socialSecurityType;

    @TableField("household_registration_type")
    private Integer householdRegistrationType;

    @TableField("social_security_base")
    private Integer socialSecurityBase;

    @TableField("industrial_injury_ratio")
    private BigDecimal industrialInjuryRatio;

    @TableField("social_security_notes")
    private String socialSecurityNotes;

    @TableField("provident_fund_city_id")
    private String providentFundCityId;

    @TableField("provident_fund_city")
    private String providentFundCity;

    @TableField("provident_fund_base")
    private Integer providentFundBase;

    @TableField("enterprise_proportion")
    private BigDecimal enterpriseProportion;

    @TableField("personal_proportion")
    private BigDecimal personalProportion;

    @TableField("enterprise_provident_fund_payment")
    private String enterpriseProvidentFundPayment;

    @TableField("personal_provident_fund_payment")
    private String personalProvidentFundPayment;

    @TableField("provident_fund_notes")
    private String providentFundNotes;

    @TableField("social_security_month")
    private String socialSecurityMonth;

    @TableField("provident_fund_month")
    private String providentFundMonth;

    @TableField("social_security_computer_number")
    private String socialSecurityComputerNumber;

    @TableField("provident_fund_account")
    private String providentFundAccount;

    @TableField("enterprises_pay_social_security_this_month")
    private Integer enterprisesPaySocialSecurityThisMonth;

    @TableField("enterprises_pay_provident_fund_this_month")
    private Integer enterprisesPayProvidentFundThisMonth;
}
package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("ss_archive_details")
public class SsArchiveDetails {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String archiveId;

    private String userId;

    private String username;

    private String timeOfEntry;

    private String mobile;

    private String idNumber;

    private String theHighestDegreeOfEducation;

    private String openingBank;

    private String bankCardNumber;

    private String firstLevelDepartment;

    private String twoLevelDepartment;

    private String workingCity;

    private String socialSecurityComputerNumber;

    private String providentFundAccount;

    private String leaveDate;

    private Integer householdRegistrationType;

    private String participatingInTheCity;

    private String socialSecurityMonth;

    private Integer socialSecurityBase;

    private Integer socialSecurity;

    private Integer socialSecurityEnterprise;

    private Integer socialSecurityIndividual;

    private String providentFundCity;

    private String providentFundMonth;

    private Integer providentFundBase;

    private Integer accumulationFundEnterpriseBase;

    private BigDecimal proportionOfProvidentFundEnterprises;

    private Integer individualBaseOfProvidentFund;

    private BigDecimal personalRatioOfProvidentFund;

    private Integer totalProvidentFund;

    private Integer providentFundEnterprises;

    private Integer providentFundIndividual;

    private String pensionEnterpriseBase;

    private BigDecimal proportionOfPensionEnterprises;

    private Integer pensionEnterprise;

    private Integer personalPensionBase;

    private BigDecimal personalPensionRatio;

    private Integer oldAgeIndividual;

    private String unemploymentEnterpriseBase;

    private BigDecimal proportionOfUnemployedEnterprises;

    private Integer unemployedEnterprise;

    private Integer theNumberOfUnemployedIndividuals;

    private BigDecimal percentageOfUnemployedIndividuals;

    private Integer unemployedIndividual;

    private String medicalEnterpriseBase;

    private BigDecimal proportionOfMedicalEnterprises;

    private Integer medicalEnterprise;

    private Integer medicalPersonalBase;

    private BigDecimal medicalPersonalRatio;

    private Integer medicalIndividual;

    private String baseOfIndustrialInjuryEnterprises;

    private BigDecimal proportionOfIndustrialInjuryEnterprises;

    private Integer industrialInjuryEnterprise;

    private String fertilityEnterpriseBase;

    private BigDecimal proportionOfFertilityEnterprises;

    private Integer childbearingEnterprise;

    private String baseOfSeriousIllness;

    private BigDecimal proportionOfSeriouslyIllEnterprises;

    private Integer bigDiseaseEnterprise;

    private Integer personalBaseOfSeriousIllness;

    private BigDecimal personalProportionOfSeriousIllness;

    private Integer aPersonOfGreatDisease;

    private String providentFundNotes;

    private String socialSecurityNotes;

    private String yearsMonth;

    private LocalDateTime createTime;
}
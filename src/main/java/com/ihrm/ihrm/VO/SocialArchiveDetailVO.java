package com.ihrm.ihrm.VO;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SocialArchiveDetailVO {
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
    private String proportionOfPensionEnterprises;
    private String pensionEnterprise;
    private String personalPensionBase;
    private String personalPensionRatio;
    private String oldAgeIndividual;

    private String unemploymentEnterpriseBase;
    private String proportionOfUnemployedEnterprises;
    private String unemployedEnterprise;
    private String theNumberOfUnemployedIndividuals;
    private String percentageOfUnemployedIndividuals;
    private String unemployedIndividual;

    private String medicalEnterpriseBase;
    private String proportionOfMedicalEnterprises;
    private String medicalEnterprise;
    private String medicalPersonalBase;
    private String medicalPersonalRatio;
    private String medicalIndividual;

    private String baseOfIndustrialInjuryEnterprises;
    private String proportionOfIndustrialInjuryEnterprises;
    private String industrialInjuryEnterprise;

    private String fertilityEnterpriseBase;
    private String proportionOfFertilityEnterprises;
    private String childbearingEnterprise;

    private String baseOfSeriousIllness;
    private String proportionOfSeriouslyIllEnterprises;
    private String bigDiseaseEnterprise;
    private String personalBaseOfSeriousIllness;
    private String personalProportionOfSeriousIllness;
    private String aPersonOfGreatDisease;

    private String providentFundNotes;
    private String socialSecurityNotes;
    private String yearsMonth;
}
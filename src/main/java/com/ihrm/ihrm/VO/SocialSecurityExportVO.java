package com.ihrm.ihrm.VO;

import lombok.Data;

@Data
public class SocialSecurityExportVO {
    private String username;
    private String mobile;
    private String departmentName;
    private String socialSecurityBase;
    private String providentFundBase;
    private String socialSecurityEnterprise;
    private String socialSecurityIndividual;
    private String providentFundEnterprises;
    private String providentFundIndividual;
}
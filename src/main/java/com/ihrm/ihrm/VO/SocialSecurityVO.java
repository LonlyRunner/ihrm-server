package com.ihrm.ihrm.VO;

import lombok.Data;

@Data
public class SocialSecurityVO {
    private String id;
    private String username;
    private String mobile;
    private String workNumber;
    private String departmentName;
    private String timeOfEntry;
    private String leaveTime;
    
    private String participatingInTheCityId;
    private String participatingInTheCity;
    private Integer socialSecurityBase;
    
    private String providentFundCityId;
    private String providentFundCity;
    private Integer providentFundBase;
}
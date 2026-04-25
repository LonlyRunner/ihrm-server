package com.ihrm.ihrm.VO;

import lombok.Data;

@Data
public class SocialSecurityItemVO {
    private String id;
    private String cityId;
    private String paymentItemId;
    private Boolean switchCompany;
    private Integer scaleCompany;
    private Boolean switchPersonal;
    private Integer scalePersonal;
    private String name;
}
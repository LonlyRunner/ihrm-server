package com.ihrm.ihrm.VO;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SocialSecurityHistoryVO {
    private String id;
    private String companyId;
    private String yearsMonth;
    private LocalDateTime creationTime;
    private BigDecimal enterprisePayment;
    private BigDecimal personalPayment;
    private BigDecimal total;
}
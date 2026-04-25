package com.ihrm.ihrm.VO;

import lombok.Data;

@Data
public class HomeDataVO {
    private Integer employeeTotal;          // 组织总人数
    private Integer regularEmployeeTotal;   // 正式员工总数
    private Integer contractSignTotal;      // 合同待签署总数
    private Integer toBeEmployed;           // 待入职人数
    private Integer toBeConfirmed;          // 本月待转正人数
    private Integer toBeDismissed;          // 本月待离职人数
    private Integer interfaceAccessTotal;   // 接口总访问数
    private DeclarationDataVO socialInsurance;  // 社保申报数据
    private DeclarationDataVO providentFund;    // 公积金申报数据
}
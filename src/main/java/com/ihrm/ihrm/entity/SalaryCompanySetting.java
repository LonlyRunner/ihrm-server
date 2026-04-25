package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("salary_company_setting")
public class SalaryCompanySetting extends BaseEntity {

    private String companyId;

    /**
     * 0未设置 1已设置
     */
    private Integer isSettings;

    /**
     * 年月
     */
    private String dataMonth;
}
package com.ihrm.ihrm.VO;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SysUserExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("mobile")
    private String mobile;

    @ExcelProperty("password")
    private String password;

    @ExcelProperty("username")
    private String username;

    @ExcelProperty("create_time")
    private Date createTime;

    @ExcelProperty("update_time")
    private Date updateTime;

    @ExcelProperty("is_deleted")
    private Integer isDeleted;

    @ExcelProperty("work_number")
    private String workNumber;

    @ExcelProperty("department_name")
    private String departmentName;

    @ExcelProperty("time_of_entry")
    private Date timeOfEntry;

    @ExcelProperty("form_of_employment")
    private Integer formOfEmployment;

    @ExcelProperty("staff_photo")
    private String staffPhoto;

    @ExcelProperty("state")
    private Integer state;

    @ExcelProperty("departmentId")
    private String departmentId;
}
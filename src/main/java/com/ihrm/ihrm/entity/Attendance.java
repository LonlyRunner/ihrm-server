package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("attendance")
public class Attendance extends BaseEntity {
    private String employeeId;
    private String employeeName;
    private String mobile;
    private String departmentId;
    private String departmentName;
    private String attendanceDate;
    private String checkInTime;
    private String checkOutTime;
    private String status;
    private String remark;
}
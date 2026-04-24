package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("leave_setting")
public class LeaveSetting {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String companyId;
    private String departmentId;
    private String leaveType;
    private Integer isEnable;
}
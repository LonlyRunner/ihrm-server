package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String mobile;
    private String username;
    private String password;
    private String workNumber;
    // ✅ 强制指定数据库字段名 departmentId
    @TableField("departmentId")
    private String departmentId;
    private String departmentName;

    private LocalDateTime timeOfEntry;
    private Integer formOfEmployment;
    private String staffPhoto;
    private Integer state;
}
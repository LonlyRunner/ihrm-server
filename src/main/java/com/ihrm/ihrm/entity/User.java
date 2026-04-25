package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String username;

    private String password;

    private String mobile;

    private String email;

    private Integer status;

    private Integer employeeType;

    private String departmentId;

    private LocalDateTime entryDate;

    private LocalDateTime probationEndDate;

    private LocalDateTime leaveDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
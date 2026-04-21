package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("department")
public class Department {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long pid;
    private String name;
    private String code;
    @TableField("manager_id")    // 加上这行
    private Long managerId;
    private String introduce;
    private LocalDateTime createTime;
    private String managerName;
}
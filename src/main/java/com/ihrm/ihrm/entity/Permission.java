package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_permission")
public class Permission {
    private Integer id;             // 权限点id
    private String name;           // 权限点名字
    private String description;    // 权限点描述
    private Integer type;          // 1页面路由 2按钮
    private String code;           // 权限标识
    private Integer pid;           // 父id
    // 只需要加个注解映射！
    @TableField("en_visible")
    private Integer enVisible;     // 0关闭 1开启
}
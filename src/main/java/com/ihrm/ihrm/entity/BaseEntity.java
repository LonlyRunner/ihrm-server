package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    
    @TableField(exist = false)
    private String createBy;
    
    @TableField(exist = false)
    private String createDate;
    
    @TableField(exist = false)
    private String updateBy;
    
    @TableField(exist = false)
    private String updateDate;
    
    @TableField(exist = false)
    private String remarks;
}
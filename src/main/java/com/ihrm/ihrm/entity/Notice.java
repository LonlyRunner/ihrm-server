package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_notice")
public class Notice {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String title;

    private String content;

    private Integer type;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
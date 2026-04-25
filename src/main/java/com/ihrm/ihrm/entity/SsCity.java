package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("ss_city")
public class SsCity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private Integer cityType;

    private String provinceId;

    private LocalDateTime createTime;
}
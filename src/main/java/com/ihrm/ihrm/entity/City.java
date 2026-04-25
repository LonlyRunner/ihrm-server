package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("city")
public class City extends BaseEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    private LocalDateTime createTime;
}
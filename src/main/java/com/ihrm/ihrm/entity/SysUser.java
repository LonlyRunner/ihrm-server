package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sys_user")
@Schema(description = "用户")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "密码")
    private String password;

    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer isDeleted;
}
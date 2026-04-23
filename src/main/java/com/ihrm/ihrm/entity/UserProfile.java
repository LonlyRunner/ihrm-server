package com.ihrm.ihrm.entity;

import lombok.Data;

@Data
public class UserProfile {
    private String userId;              // 用户ID
    private String mobile;            // 手机号
    private String username;          // 用户名
    private UserRoles roles;          // 权限
    private String companyId;         // 公司ID
    private String company;           // 公司名
    private String city;              // 城市
    private String staffPhoto;        // 头像
}
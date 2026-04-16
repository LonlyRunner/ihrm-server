package com.ihrm.ihrm.entity;

import lombok.Data;
import java.util.List;

@Data
public class UserRoles {
    private List<String> menus;   // 菜单权限
    private List<String> points;  // 按钮权限
}
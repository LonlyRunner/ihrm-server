package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.RolePermission;
import com.ihrm.ihrm.mapper.RolePermissionMapper;
import com.ihrm.ihrm.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限关联表 服务层实现
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
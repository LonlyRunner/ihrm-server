package com.ihrm.ihrm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.ihrm.entity.Role;
import com.ihrm.ihrm.entity.RolePermission;
import com.ihrm.ihrm.service.RolePermissionService;
import com.ihrm.ihrm.service.RoleService;
import com.ihrm.ihrm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role") // 这里必须一模一样
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    // 分页查询角色列表
    @GetMapping
    public R getRoleList(
            @RequestHeader("Authorization") String token,
            @RequestParam(defaultValue = "0") Integer page,  // 加默认值
            @RequestParam(defaultValue = "10") Integer pagesize) {

        Page<Role> pageParam = new Page<>(page, pagesize);
        IPage<Role> pageResult = roleService.page(pageParam);

        return R.success(pageResult);
    }

    // 新增角色
    @PostMapping
    public R addRole(
            @RequestHeader("Authorization") String token,
            @RequestBody Role role) {
        roleService.save(role);
        Map<String, Object> map = new HashMap<>();
        map.put("id", role.getId());
        return R.success(map);
    }

    // 修改角色
    @PutMapping("/{id}")
    public R updateRole(
            @RequestHeader("Authorization") String token,
            @PathVariable String id,  // String
            @RequestBody Role role
    ) {
        role.setId(id);
        roleService.updateById(role);
        return R.success("修改成功");
    }
//    删除角色
    @DeleteMapping("/{id}")
    public R deleteRole(
            @RequestHeader("Authorization") String token,
            @PathVariable String id  // String
    ) {
        roleService.removeById(id);
        return R.success("删除成功");
    }

    /**
     * 获取-已启用的角色列表
     */
    @GetMapping("/list/enabled")
    public R getEnabledRoleList() {
        // 只查询 state = 1（已启用）的角色
        LambdaQueryWrapper<Role> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Role::getState, 1); // 你的字段是 state，不是 status

        List<Role> list = roleService.list(wrapper);
        return R.success(list);
    }

    /**
     * 分配权限-角色
     */
    @PutMapping("/assignPrem")
    public R assignPrem(@RequestBody Map<String, Object> param) {
        // 1. 获取参数（兼容 String id + 数字权限数组）
        String roleId = param.get("id").toString();
        List<Integer> permIds = (List<Integer>) param.get("permIds");

        // 2. 删除该角色原有权限
        LambdaQueryWrapper<RolePermission> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionService.remove(wrapper);

        // 3. 批量插入新权限
        List<RolePermission> list = new ArrayList<>();
        for (Integer permId : permIds) {
            RolePermission rp = new RolePermission();
            rp.setRoleId(roleId);
            rp.setPermissionId(permId.toString());
            list.add(rp);
        }
        rolePermissionService.saveBatch(list);

        // 4. 按文档返回 data:null
        return R.success(null);
    }

    /**
     * 获取-角色详情
     */
    @GetMapping("/{id}")
    public R getRoleInfo(@PathVariable String id) {
        // 1. 查询角色基本信息
        Role role = roleService.getById(id);
        if (role == null) {
            return R.fail("角色不存在");
        }

        // 2. 查询该角色拥有的权限ID列表
        LambdaQueryWrapper<RolePermission> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RolePermission::getRoleId, id);
        List<RolePermission> rolePermissions = rolePermissionService.list(wrapper);

        // 3. 提取 permIds 数组
        List<Integer> permIds = new ArrayList<>();
        for (RolePermission rp : rolePermissions) {
            permIds.add(Integer.parseInt(rp.getPermissionId()));
        }

        // 4. 组装返回数据（包含所有字段：id,name,description,state,permIds）
        Map<String, Object> data = new HashMap<>();
        data.put("id", role.getId());
        data.put("name", role.getName());
        data.put("description", role.getDescription());
        data.put("state", role.getState());
        data.put("permIds", permIds);

        return R.success(data);
    }


}
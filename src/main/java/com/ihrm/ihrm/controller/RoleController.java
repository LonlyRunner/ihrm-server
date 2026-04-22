package com.ihrm.ihrm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.ihrm.entity.Role;
import com.ihrm.ihrm.service.RoleService;
import com.ihrm.ihrm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sys/role") // 这里必须一模一样
public class RoleController {

    @Autowired
    private RoleService roleService;

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
}
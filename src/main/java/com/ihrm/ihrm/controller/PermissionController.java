package com.ihrm.ihrm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ihrm.ihrm.entity.Permission;
import com.ihrm.ihrm.service.PermissionService;
import com.ihrm.ihrm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sys/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 获取-权限点列表
     */
    @GetMapping
    public R getPermissionList() {
        // 1. 查询全部权限点
        List<Permission> list = permissionService.list();

        // 2. 按文档要求返回数组
        return R.success(list);
    }

    /**
     * 新增权限点
     */
    @PostMapping
    public R addPermission(@RequestBody Permission permission) {
        // 1. 校验：同一父级下 name 不能重复
        LambdaQueryWrapper<Permission> nameWrapper = Wrappers.lambdaQuery();
        nameWrapper.eq(Permission::getPid, permission.getPid())
                .eq(Permission::getName, permission.getName());
        if (permissionService.count(nameWrapper) > 0) {
            return R.fail("当前层级下权限点名称已存在");
        }

        // 2. 校验：code 全局唯一
        LambdaQueryWrapper<Permission> codeWrapper = Wrappers.lambdaQuery();
        codeWrapper.eq(Permission::getCode, permission.getCode());
        if (permissionService.count(codeWrapper) > 0) {
            return R.fail("权限标识已存在");
        }

        // 3. 保存
        permissionService.save(permission);

        // 4. 按文档返回完整对象（前端需要全部字段）
        return R.success(permission);
    }


    /**
     * 修改权限点
     */
    @PutMapping("/{id}")
    public R updatePermission(
            @PathVariable String id,
            @RequestBody Permission permission) {

        // 把路径上的ID强制设置，保证一致性
        permission.setId(Integer.valueOf(id));

        // ===================== 校验1：同级权限名不能重复（排除自己） =====================
        LambdaQueryWrapper<Permission> nameQuery = Wrappers.lambdaQuery();
        nameQuery.eq(Permission::getPid, permission.getPid())
                .eq(Permission::getName, permission.getName())
                .ne(Permission::getId, permission.getId()); // 不包含自己

        if (permissionService.count(nameQuery) > 0) {
            return R.fail("当前层级下权限点名称已存在");
        }

        // ===================== 校验2：权限标识全局唯一（排除自己） =====================
        LambdaQueryWrapper<Permission> codeQuery = Wrappers.lambdaQuery();
        codeQuery.eq(Permission::getCode, permission.getCode())
                .ne(Permission::getId, permission.getId()); // 不包含自己

        if (permissionService.count(codeQuery) > 0) {
            return R.fail("权限标识已存在");
        }

        // ===================== 执行更新 =====================
        permissionService.updateById(permission);

        // ===================== 按文档返回完整对象 =====================
        return R.success(permission);
    }
    /**
     * 删除-权限点
     */
    @DeleteMapping("/{id}")
    public R deletePermission(@PathVariable String id) {
        int permissionId = Integer.parseInt(id);

        // 1. 校验：如果有子权限，不能删除
        LambdaQueryWrapper<Permission> query = Wrappers.lambdaQuery();
        query.eq(Permission::getPid, permissionId);

        if (permissionService.count(query) > 0) {
            return R.fail("该权限下存在子权限，无法删除！");
        }

        // 2. 执行删除
        permissionService.removeById(permissionId);

        // 3. 按文档返回 data: null
        return R.success(null);
    }



    /**
     * 获取-权限点详情
     */
    @GetMapping("/{id}")
    public R getPermissionInfo(@PathVariable String id) {
        // 根据ID查询详情
        Permission permission = permissionService.getById(id);

        // 直接返回（字段完全匹配文档）
        return R.success(permission);
    }
}
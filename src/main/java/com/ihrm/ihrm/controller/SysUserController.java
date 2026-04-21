package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.entity.SysUser;
import com.ihrm.ihrm.service.SysUserService;
import com.ihrm.ihrm.util.JwtUtil;
import com.ihrm.ihrm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtUtil jwtUtil;

    // ======================
    // 修改密码
    // ======================
    @PutMapping("/updatePass")
    public R updatePass(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, String> map) {

        String oldPassword = map.get("oldPassword");
        String newPassword = map.get("newPassword");

        Long userId = jwtUtil.getUserIdFromToken(token);
        sysUserService.updatePassword(userId, oldPassword, newPassword);

        return R.success("密码修改成功");
    }
    // ======================
    // 获取部门负责人列表（只返回 id + username）
    // ======================
    @GetMapping("/simple")
    public R getSimpleUserList(@RequestHeader("Authorization") String token) {
        List<SysUser> userList = sysUserService.list();

        // 只保留 id 和 username，完全匹配接口文档
        List<Map<String, Object>> result = userList.stream().map(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId().toString()); // 返回字符串格式id
            map.put("username", user.getUsername());
            return map;
        }).collect(Collectors.toList());

        return R.success(result);
    }
}
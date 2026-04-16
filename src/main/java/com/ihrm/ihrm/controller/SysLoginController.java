package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.service.SysUserService;
import com.ihrm.ihrm.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/sys")
@Tag(name = "系统登录", description = "人资项目-登录")
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "登录", description = "内部OA项目，无注册功能")
    @PostMapping("/login")
    public R<String> login(@RequestBody Map<String, String> map) {
        String mobile = map.get("mobile");
        String password = map.get("password");
        String token = sysUserService.login(mobile, password);
        return R.success(token);
    }
}
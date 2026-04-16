package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.entity.SysUser;
import com.ihrm.ihrm.entity.UserProfile;
import com.ihrm.ihrm.entity.UserRoles;
import com.ihrm.ihrm.service.SysUserService;
import com.ihrm.ihrm.util.JwtUtil;
import com.ihrm.ihrm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sys")
public class SysProfileController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/profile")
    public R<UserProfile> profile(@RequestHeader("Authorization") String token) {
        Long userId = jwtUtil.getUserIdFromToken(token);
        SysUser user = sysUserService.getById(userId);

        UserProfile profile = new UserProfile();
        profile.setUserId(user.getId());
        profile.setMobile(user.getMobile());
        profile.setUsername(user.getUsername());

        UserRoles roles = new UserRoles();
        roles.setMenus(List.of("system","user","role"));
        roles.setPoints(List.of("add","edit","del"));
        profile.setRoles(roles);

        profile.setCompanyId("1");
        profile.setCompany("黑马程序员");
        profile.setCity("北京");
        profile.setStaffPhoto("https://picsum.photos/200");

        return R.success(profile);
    }
}
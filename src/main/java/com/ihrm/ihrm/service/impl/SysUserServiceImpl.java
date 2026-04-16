package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.SysUser;
import com.ihrm.ihrm.mapper.SysUserMapper;
import com.ihrm.ihrm.service.SysUserService;
import com.ihrm.ihrm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Deleted:import javax.annotation.Resource;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String login(String mobile, String password) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getMobile, mobile);
        SysUser user = this.getOne(wrapper);

        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("手机号或密码错误");
        }

        // 生成正确格式的 token
        return jwtUtil.createToken(user.getId());
    }
}

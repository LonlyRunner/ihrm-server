package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.SysUser;
import com.ihrm.ihrm.mapper.SysUserMapper;
import com.ihrm.ihrm.service.SysUserService;
import com.ihrm.ihrm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.annotation.Resource;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    // 🔥 注入 JwtUtil（修复无法解析 jwUtil 错误）
    @Autowired
    private JwtUtil jwtUtil;

    // 登录方法
    @Override
    public String login(String mobile, String password) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getMobile, mobile);
        SysUser user = this.getOne(wrapper);

        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("账号或密码错误");
        }

        return jwtUtil.createToken(user.getId());
    }

    // 修改密码
    @Override
    public void updatePassword(String userId, String oldPassword, String newPassword) {
        SysUser user = this.getById(userId);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("旧密码错误");
        }

        LambdaUpdateWrapper<SysUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysUser::getId, userId);
        updateWrapper.set(SysUser::getPassword, newPassword);
        this.update(updateWrapper);
    }
    // 得到用户分页列表
    @Override
    public IPage<SysUser> getUserPage(Page<SysUser> pageParam, String keyword, String departmentId) {
        return lambdaQuery()
                .like(keyword != null, SysUser::getUsername, keyword)
                .eq(!"0".equals(departmentId), SysUser::getDepartmentId, departmentId)
                .page(pageParam);
    }
}
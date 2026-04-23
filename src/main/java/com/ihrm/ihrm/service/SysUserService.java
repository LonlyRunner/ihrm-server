package com.ihrm.ihrm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ihrm.ihrm.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    String login(String mobile, String password);
    void updatePassword(String userId, String oldPassword, String newPassword);

    IPage<SysUser> getUserPage(Page<SysUser> pageParam, String keyword, String departmentId);
}
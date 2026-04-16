package com.ihrm.ihrm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihrm.ihrm.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    String login(String mobile, String password);
}
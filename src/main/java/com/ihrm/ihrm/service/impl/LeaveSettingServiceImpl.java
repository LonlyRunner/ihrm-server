package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.LeaveSetting;
import com.ihrm.ihrm.mapper.LeaveSettingMapper;
import com.ihrm.ihrm.service.LeaveSettingService;
import org.springframework.stereotype.Service;

@Service
public class LeaveSettingServiceImpl extends ServiceImpl<LeaveSettingMapper, LeaveSetting> implements LeaveSettingService {
}
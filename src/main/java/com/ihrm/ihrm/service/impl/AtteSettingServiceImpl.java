package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.AtteSetting;
import com.ihrm.ihrm.mapper.AtteSettingMapper;
import com.ihrm.ihrm.service.AtteSettingService;
import org.springframework.stereotype.Service;

@Service
public class AtteSettingServiceImpl extends ServiceImpl<AtteSettingMapper, AtteSetting> implements AtteSettingService {
}
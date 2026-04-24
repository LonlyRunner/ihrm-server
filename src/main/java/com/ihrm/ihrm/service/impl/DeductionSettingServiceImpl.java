package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.DeductionSetting;
import com.ihrm.ihrm.mapper.DeductionSettingMapper;
import com.ihrm.ihrm.service.DeductionSettingService;
import org.springframework.stereotype.Service;

@Service
public class DeductionSettingServiceImpl extends ServiceImpl<DeductionSettingMapper, DeductionSetting> implements DeductionSettingService {
}
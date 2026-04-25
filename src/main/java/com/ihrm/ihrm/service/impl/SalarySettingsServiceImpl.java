package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.SalarySettings;
import com.ihrm.ihrm.mapper.SalarySettingsMapper;
import com.ihrm.ihrm.service.SalarySettingsService;
import org.springframework.stereotype.Service;

@Service
public class SalarySettingsServiceImpl extends ServiceImpl<SalarySettingsMapper, SalarySettings> implements SalarySettingsService {
}
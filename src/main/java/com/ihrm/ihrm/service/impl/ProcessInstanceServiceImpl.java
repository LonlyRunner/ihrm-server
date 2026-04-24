package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.ProcessInstance;
import com.ihrm.ihrm.mapper.ProcessInstanceMapper;
import com.ihrm.ihrm.service.ProcessInstanceService;
import org.springframework.stereotype.Service;

@Service
public class ProcessInstanceServiceImpl extends ServiceImpl<ProcessInstanceMapper, ProcessInstance> implements ProcessInstanceService {
}
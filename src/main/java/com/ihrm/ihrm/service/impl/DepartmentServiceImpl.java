package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.Department;
import com.ihrm.ihrm.mapper.DepartmentMapper;
import com.ihrm.ihrm.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public List<Department> getDepartmentList() {
        // 查询所有部门
        return list();
    }
}
package com.ihrm.ihrm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihrm.ihrm.entity.Department;
import java.util.List;

public interface DepartmentService extends IService<Department> {
    List<Department> getDepartmentList();
}
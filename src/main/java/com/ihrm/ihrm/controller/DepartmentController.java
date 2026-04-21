package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.entity.Department;
import com.ihrm.ihrm.service.DepartmentService;
import com.ihrm.ihrm.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // 获取部门列表
    @GetMapping
    public R getDepartmentList(@RequestHeader("Authorization") String token) {
        List<Department> list = departmentService.list();
        return R.success(list);
    }

    // 新增部门
    @PostMapping
    public R addDepartment(@RequestHeader("Authorization") String token,
                           @RequestBody Department department) {
        department.setCreateTime(LocalDateTime.now());
        departmentService.save(department);

        Map<String, Object> data = new HashMap<>();
        data.put("id", department.getId());
        return R.success(data);
    }
}
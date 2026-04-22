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
    /**
     * 获取部门详情
     */
    @GetMapping("/{id}")
    public R getDepartmentDetail(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {

        Department department = departmentService.getById(id);
        return R.success(department);
    }

    // 修改部门详情
    @PutMapping("/{id}")
    public R updateDepartment(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id,
            @RequestBody Department department) {
        // 确保ID一致
        department.setId(id);
        departmentService.updateById(department);
        return R.success("修改成功");
    }
    // 删除部门
    @DeleteMapping("/{id}")
    public R deleteDepartment(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {
        departmentService.removeById(id);
        return R.success("删除成功");
    }
}
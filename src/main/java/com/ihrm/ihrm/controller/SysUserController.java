package com.ihrm.ihrm.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.ihrm.VO.SysUserExcelVO;
import com.ihrm.ihrm.entity.SysUser;
import com.ihrm.ihrm.service.SysUserService;
import com.ihrm.ihrm.util.R;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    // ====================== 1. 员工分页列表（解决之前404） ======================
    @GetMapping
    public R getUserPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String departmentId,
            @RequestParam(required = false) Integer total,
            @RequestParam(required = false) List<Integer> approvalsTypeChecks,
            @RequestParam(required = false) List<Integer> approvalsStateChecks,
            @RequestParam(required = false) List<String> departmentChecks
    ) {
        IPage<SysUser> iPage = new Page<>(page, pageSize);

        // 构建查询条件
        var query = userService.lambdaQuery();
        
        // 关键词搜索
        if (keyword != null && !keyword.isEmpty()) {
            query.like(SysUser::getUsername, keyword);
        }
        
        // 部门筛选
        if (departmentId != null && !"0".equals(departmentId)) {
            query.eq(SysUser::getDepartmentId, departmentId);
        }
        
        // 部门复选框筛选
        if (departmentChecks != null && !departmentChecks.isEmpty()) {
            query.in(SysUser::getDepartmentId, departmentChecks);
        }
        
        // 执行查询
        IPage<SysUser> result = query.page(iPage);
        
        return R.success(result);
    }

    // ====================== 2. 导出Excel ======================
    @GetMapping("/export")
    public void exportUserExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("员工列表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), SysUserExcelVO.class)
                .sheet("员工列表")
                .doWrite(userService.list());
    }

    // ====================== 3. 下载导入模板（✅ 正确路径，解决你现在的404） ======================
    @GetMapping("/import/template")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("员工全字段导入模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        // 所有字段表头
        List<List<String>> head = Arrays.asList(
                Arrays.asList("id"),
                Arrays.asList("mobile"),
                Arrays.asList("password"),
                Arrays.asList("username"),
                Arrays.asList("create_time"),
                Arrays.asList("update_time"),
                Arrays.asList("is_deleted"),
                Arrays.asList("work_number"),
                Arrays.asList("department_name"),
                Arrays.asList("time_of_entry"),
                Arrays.asList("form_of_employment"),
                Arrays.asList("staff_photo"),
                Arrays.asList("state"),
                Arrays.asList("departmentId")
        );

        EasyExcel.write(response.getOutputStream())
                .head(head)
                .sheet("员工导入模板")
                .doWrite(Collections.emptyList());
    }

    // ====================== 4. 批量导入员工（上传Excel） ======================
    @PostMapping("/import")
    public R importUser(@RequestParam("file") MultipartFile file) throws Exception {
        // 1. 读取Excel
        List<SysUserExcelVO> excelList = EasyExcel.read(file.getInputStream())
                .head(SysUserExcelVO.class)
                .sheet()
                .doReadSync();

        // 2. 转换为用户实体
        List<SysUser> userList = new ArrayList<>();
        for (SysUserExcelVO vo : excelList) {
            SysUser user = new SysUser();
            user.setUsername(vo.getUsername());
            user.setMobile(vo.getMobile());
            user.setWorkNumber(vo.getWorkNumber());
            user.setDepartmentName(vo.getDepartmentName());
            user.setPassword("123456"); // 默认密码
            user.setStaffPhoto(vo.getStaffPhoto());
            if (vo.getTimeOfEntry() != null) {
                user.setTimeOfEntry(vo.getTimeOfEntry().toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDateTime());
            }
            user.setFormOfEmployment(vo.getFormOfEmployment());
            user.setDepartmentId(vo.getDepartmentId());
            userList.add(user);
        }

        // 3. 批量保存
        userService.saveBatch(userList);

        // 4. ✅ 使用你自己的 R 类返回（完全不报错）
        return R.success("导入成功，共导入 " + userList.size() + " 人");
    }


    // ====================== 5. 删除员工 ======================
    @DeleteMapping("/{id}")
    public R deleteUser(@PathVariable String id) {
        userService.removeById(id);
        return R.success("删除成功");
    }

    // ====================== 6. 获取简单用户列表（用于下拉选择等） ======================
    @GetMapping("/simple")
    public R getSimpleUserList() {
        List<SysUser> userList = userService.lambdaQuery()
                .select(SysUser::getId, SysUser::getUsername, SysUser::getMobile)
                .list();
        return R.success(userList);
    }
    // ====================== 7. 分配角色（防止路径冲突） ======================
    @PutMapping("/assignRoles")
    public R assignRoles(@RequestBody Map<String, Object> params) {
        String userId = (String) params.get("userId");
        @SuppressWarnings("unchecked")
        List<String> roleIds = (List<String>) params.get("roleIds");

        // TODO: 实现分配角色的逻辑

        return R.success("分配成功");
    }

//    新增员工
@PostMapping
public R addUser(@RequestBody SysUser user) {
    // 保存员工
    boolean save = userService.save(user);
    if (save) {
        // 返回格式严格按照文档：data 里只返回 id
        return R.success(user.getId());
    }
    return R.fail("新增失败");
}
//得到员工详细信息
@GetMapping("/{id}")
public R getUserInfo(@PathVariable String id) {
    // 1. 查询员工
    SysUser user = userService.getById(id);

    // 2. 直接返回（字段完全匹配文档）
    return R.success(user);
}
//新增员工
@PutMapping("/{id}")
public R updateUser(@PathVariable String id, @RequestBody SysUser user) {
    // 强制把路径id set进去，保证一致性
    user.setId(id);

    // 执行更新
    userService.updateById(user);

    // 按文档要求：返回 { id: 员工ID }
    return R.success(Map.of("id", id));
}
}
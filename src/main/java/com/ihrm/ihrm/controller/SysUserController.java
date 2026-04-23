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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    // ====================== 1. 员工分页列表（解决之前404） ======================
    @GetMapping
    public Object getUserPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pagesize,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String departmentId
    ) {
        IPage<SysUser> iPage = new Page<>(page, pagesize);

        return userService.lambdaQuery()
                .like(keyword != null && !keyword.isEmpty(), SysUser::getUsername, keyword)
                .eq(departmentId != null && !"0".equals(departmentId), SysUser::getDepartmentId, departmentId)
                .page(iPage);
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
}
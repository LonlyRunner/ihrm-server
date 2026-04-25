package com.ihrm.ihrm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.ihrm.entity.Attendance;
import com.ihrm.ihrm.entity.AttendanceArchiveReport;
import com.ihrm.ihrm.entity.AttendanceMonthlyReport;
import com.ihrm.ihrm.service.AttendanceArchiveReportService;
import com.ihrm.ihrm.service.AttendanceMonthlyReportService;
import com.ihrm.ihrm.service.AttendanceService;
import com.ihrm.ihrm.util.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    @Resource
    private AttendanceArchiveReportService archiveReportService;

    @Resource
    private AttendanceMonthlyReportService monthlyReportService;

    // 分页查询考勤记录
    @GetMapping
    public R getAttendancesList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pagesize,
            @RequestParam(required = false) String deptID) {
        try {
            IPage<Attendance> attendancePage = attendanceService.lambdaQuery()
                    .eq(deptID != null && !deptID.isEmpty(), Attendance::getDepartmentId, deptID)
                    .page(new Page<>(page, pagesize));
            Map<String, Object> result = new HashMap<>();
            result.put("total", attendancePage.getTotal());
            result.put("rows", attendancePage.getRecords());
            return R.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> emptyResult = new HashMap<>();
            emptyResult.put("total", 0);
            emptyResult.put("rows", new java.util.ArrayList<>());
            return R.success(emptyResult);
        }
    }

    // 获取考勤详情
    @GetMapping("/archive/{userId}/{yearMonth}")
    public R getAtteArchiveDetail(@PathVariable String userId, @PathVariable String yearMonth) {
        try {
            java.util.Map<String, Object> data = new java.util.HashMap<>();
            // TODO: 从数据库查询归档数据
            // 示例: data = attendanceService.getArchiveDetail(userId, yearMonth);
            
            return R.success(data);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("查询失败");
        }
    }

    // 更新考勤
    @PutMapping("/{id}")
    public R updateAttendance(@PathVariable String id, @RequestBody Map<String, Object> data) {
        try {
            Attendance attendance = new Attendance();
            attendance.setId(id);
            attendance.setEmployeeId((String) data.get("userId"));
            attendance.setAttendanceDate((String) data.get("day"));
            attendance.setStatus((String) data.get("adtStatu"));
            attendance.setDepartmentId((String) data.get("departmentId"));
            attendanceService.updateById(attendance);
            return R.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("更新失败");
        }
    }

    // 获取考勤报告列表
    @GetMapping("/reports/year")
    public R getArchivingList(
            @RequestParam(required = false) String departmentId,
            @RequestParam(required = false) String year) {
        try {
            var list = archiveReportService.lambdaQuery()
                    .eq(departmentId != null && !departmentId.isEmpty(), AttendanceArchiveReport::getDepartmentId, departmentId)
                    .eq(year != null && !year.isEmpty(), AttendanceArchiveReport::getArchiveYear, year)
                    .list();
            return R.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.success(new java.util.ArrayList<>());
        }
    }

    // 获取考勤报告详情
    @GetMapping("/reports/{id}")
    public R getArchivingDetail(@PathVariable String id) {
        try {
            var report = archiveReportService.lambdaQuery()
                    .eq(AttendanceArchiveReport::getId, id)
                    .list();
            return R.success(report);
        } catch (Exception e) {
            e.printStackTrace();
            return R.success(new java.util.ArrayList<>());
        }
    }

    // 月度考勤记录归档
    @GetMapping("/archives")
    public R archives(@RequestParam(required = false) String atteDate) {
        return R.success(null);
    }

    // 新增考勤报表
    @GetMapping("/newReports")
    public R newReports(@RequestParam(required = false) String yearMonth) {
        return R.success(null);
    }

    // 获取月考勤报表数据
    @GetMapping("/reports")
    public R reportFormList(@RequestParam(required = false) String atteDate) {
        try {
            var list = monthlyReportService.lambdaQuery().list();
            return R.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.success(new java.util.ArrayList<>());
        }
    }

    // 导入考勤信息
    @PostMapping("/import")
    public R importArchive(@RequestParam("file") MultipartFile file) {
        try {
            // TODO: 实现解析Excel文件并导入考勤数据的逻辑
            return R.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("导入失败");
        }
    }

    // 文件更新
    @PutMapping("/archives/{month}")
    public R fileUpdate(@PathVariable String month, @RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 信息 获取考勤报告列表
    @GetMapping("/information")
    public R information() {
        return R.success(null);
    }

    // 提醒接口
    @PostMapping("/notify")
    public R sendNotify() {
        return R.success(null);
    }

    // 考勤状态映射常量
    private static final java.util.Map<String, String> STATUS_MAP = new java.util.HashMap<>();
    static {
        STATUS_MAP.put("1", "正常");
        STATUS_MAP.put("2", "旷工");
        STATUS_MAP.put("3", "迟到");
        STATUS_MAP.put("4", "早退");
        STATUS_MAP.put("5", "外出");
        STATUS_MAP.put("6", "出差");
        STATUS_MAP.put("7", "年假");
        STATUS_MAP.put("8", "事假");
        STATUS_MAP.put("9", "病假");
        STATUS_MAP.put("10", "婚假");
        STATUS_MAP.put("11", "丧假");
        STATUS_MAP.put("12", "产假");
        STATUS_MAP.put("13", "奖励产假");
        STATUS_MAP.put("14", "陪产假");
        STATUS_MAP.put("15", "探亲假");
        STATUS_MAP.put("16", "工伤假");
        STATUS_MAP.put("17", "调休");
        STATUS_MAP.put("18", "产检假");
        STATUS_MAP.put("19", "流产假");
        STATUS_MAP.put("20", "长期病假");
        STATUS_MAP.put("21", "补签");
        STATUS_MAP.put("22", "休息");
    }

    // 查询用户的考勤状态
    @GetMapping("/adtStatu/list")
    public R getAttendanceStatusList(@RequestParam(required = false) String userId) {
        try {
            // 定义考勤状态映射
            java.util.List<java.util.Map<String, Object>> statusList = new java.util.ArrayList<>();
            
            // 添加所有考勤状态
            for (java.util.Map.Entry<String, String> entry : STATUS_MAP.entrySet()) {
                addStatus(statusList, entry.getKey(), entry.getValue());
            }
            
            return R.success(statusList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("查询失败");
        }
    }
    
    // 辅助方法：添加状态到列表
    private void addStatus(java.util.List<java.util.Map<String, Object>> list, String code, String name) {
        java.util.Map<String, Object> status = new java.util.HashMap<>();
        status.put("adtStatu", code);
        status.put("adtStatuName", name);
        list.add(status);
    }

    // 批量-导出考勤月份报表
    @GetMapping("/historys/{yearMonth}/export")
    public void exportAttendanceReport(
            @PathVariable String yearMonth,
            @RequestParam(required = false) String fileName,
            @RequestParam(required = false, defaultValue = "1") String opType,
            jakarta.servlet.http.HttpServletResponse response) {
        try {
            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            
            // 生成文件名
            String finalFileName = fileName != null && !fileName.isEmpty() 
                    ? fileName + ".xlsx" 
                    : "批量导出考勤数据" + yearMonth + ".xlsx";
            
            // 处理文件名编码
            finalFileName = java.net.URLEncoder.encode(finalFileName, "UTF-8").replaceAll("\\+", "%");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + finalFileName);
            
            // 查询考勤数据
            java.util.List<Attendance> attendanceList = new java.util.ArrayList<>();
            // TODO: 从数据库查询考勤数据
            // 示例: attendanceList = attendanceService.getAttendanceByMonth(yearMonth, opType);
            
            // 准备数据
            java.util.List<java.util.List<Object>> dataList = new java.util.ArrayList<>();
            for (Attendance attendance : attendanceList) {
                java.util.List<Object> row = new java.util.ArrayList<>();
                row.add(attendance.getId());
                row.add(attendance.getEmployeeId());
                row.add(attendance.getEmployeeName());
                row.add(attendance.getMobile());
                row.add(attendance.getDepartmentId());
                row.add(attendance.getDepartmentName());
                row.add(attendance.getAttendanceDate());
                row.add(attendance.getCheckInTime());
                row.add(attendance.getCheckOutTime());
                row.add(getStatusName(attendance.getStatus())); // 转换状态码为中文
                row.add(attendance.getRemark());
                dataList.add(row);
            }
            
            // 写入数据
            com.alibaba.excel.EasyExcel.write(response.getOutputStream())
                    .sheet("考勤报表")
                    .head(new java.util.ArrayList<java.util.List<String>>() {
                        {
                            add(java.util.Arrays.asList("ID", "员工ID", "员工姓名", "手机号", "部门ID", "部门名称", "考勤日期", "上班时间", "下班时间", "状态", "备注"));
                        }
                    })
                    .doWrite(dataList);
            
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常
            try {
                response.getWriter().write("导出失败：" + e.getMessage());
            } catch (java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    // 根据状态码获取状态名称
    private String getStatusName(String statusCode) {
        return STATUS_MAP.getOrDefault(statusCode, "未知");
    }
}
package com.ihrm.ihrm.controller; 
 
 import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper; 
 import com.baomidou.mybatisplus.core.toolkit.Wrappers; 
 import com.ihrm.ihrm.entity.AtteSetting; 
 import com.ihrm.ihrm.entity.DeductionSetting; 
 import com.ihrm.ihrm.entity.LeaveSetting; 
 import com.ihrm.ihrm.service.AtteSettingService; 
 import com.ihrm.ihrm.service.DeductionSettingService; 
 import com.ihrm.ihrm.service.LeaveSettingService; 
 import com.ihrm.ihrm.util.R;
 import jakarta.annotation.Resource;
 import org.springframework.web.bind.annotation.*;
 
// import javax.annotation.Resource;
 import java.util.List; 
 
 @RestController 
 @RequestMapping("/cfg") 
 public class CfgController { 
 
     @Resource
     private AtteSettingService atteSettingService; 
 
     @Resource 
     private LeaveSettingService leaveSettingService; 
 
     @Resource 
     private DeductionSettingService deductionSettingService; 
 
     // ==================== 1. 保存考勤设置 ====================
    @PutMapping("/atte")
    public R saveAtte(@RequestBody AtteSetting atteSetting) {
        atteSettingService.saveOrUpdate(atteSetting);
        return R.success(null);
    }

    // ==================== 2. 根据部门id查询考勤设置 ====================
    @PostMapping("/atte/item")
    public R getAtteByDeptId(@RequestBody java.util.Map<String, Object> params) {
        String departmentId = String.valueOf(params.get("departmentId"));
        LambdaQueryWrapper<AtteSetting> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(AtteSetting::getDepartmentId, departmentId);
        AtteSetting setting = atteSettingService.getOne(wrapper);
        return R.success(setting);
    }

    // ==================== 3. 保存请假规则 ====================
    @PutMapping("/leave")
    public R saveLeave(@RequestBody LeaveSetting leaveSetting) {
        leaveSettingService.saveOrUpdate(leaveSetting);
        return R.success(null);
    }

    // ==================== 4. 根据部门查询请假规则 ====================
    @PostMapping("/leave/list")
    public R getLeaveList(@RequestBody java.util.Map<String, Object> params) {
        String departmentId = String.valueOf(params.get("departmentId"));
        LambdaQueryWrapper<LeaveSetting> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(LeaveSetting::getDepartmentId, departmentId);
        List<LeaveSetting> list = leaveSettingService.list(wrapper);
        return R.success(list);
    }

    // ==================== 5. 保存考勤扣款规则（数组） ====================
    @PutMapping("/deduction")
    public R saveDeduction(@RequestBody List<DeductionSetting> list) {
        String departmentId = list.get(0).getDepartmentId();

        LambdaQueryWrapper<DeductionSetting> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DeductionSetting::getDepartmentId, departmentId);
        deductionSettingService.remove(wrapper);

        deductionSettingService.saveBatch(list);
        return R.success(null);
    }

    // ==================== 6. 根据部门查询扣款规则 ====================
    @PostMapping("/ded/list")
    public R getDeductions(@RequestBody java.util.Map<String, Object> params) {
        String departmentId = String.valueOf(params.get("departmentId"));
        LambdaQueryWrapper<DeductionSetting> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(DeductionSetting::getDepartmentId, departmentId);
        List<DeductionSetting> list = deductionSettingService.list(wrapper);
        return R.success(list);
    }

    // ==================== 6. 保存加班规则 ====================
    @PutMapping("/extDuty")
    public R saveExtDuty(@RequestBody Object obj) {
        // 按文档格式返回
        return R.success(null);
    }

    // ==================== 7. 查询加班规则 ====================
    @PostMapping("/extDuty/item")
    public R getExtDuty(@RequestBody java.util.Map<String, Object> params) {
        String departmentId = String.valueOf(params.get("departmentId"));
        return R.success(null);
    } 
 
     // ==================== 8. 考勤通用配置 ==================== 
     @GetMapping("/config") 
     public R getConfig() { 
         return R.success(null); 
     } 
 }
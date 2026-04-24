package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.util.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserProcessController {

    // 获取审批列表
    @PutMapping("/process/instance/{page}/{pageSize}")
    public R getApprovalList(@PathVariable Integer page, @PathVariable Integer pageSize, @RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 获取审批信息
    @GetMapping("/process/instance/getById/{id}")
    public R getInformation(@PathVariable String id) {
        return R.success(null);
    }

    // 获取流程详情
    @GetMapping("/process/instance/{id}")
    public R getApprovalsDetail(@PathVariable String id) {
        return R.success(null);
    }

    // 获取流程任务详情
    @GetMapping("/process/instance/tasks/{id}")
    public R getApprovalsTaskDetail(@PathVariable String id) {
        return R.success(null);
    }

    // 下载图片
    @GetMapping("/process/buss/showBussImgById/{picture_id}")
    public R downImg(@PathVariable String picture_id) {
        return R.success(null);
    }

    // 获取流程列表
    @GetMapping("/process/definition")
    public R getFlowList(@RequestParam Map<String, Object> params) {
        return R.success(null);
    }

    // 挂起
    @GetMapping("/process/suspend/{processKey}")
    public R suspend(@PathVariable String processKey, @RequestParam Map<String, Object> params) {
        return R.success(null);
    }

    // 启动流程
    @PostMapping("/process/startProcess")
    public R startProcess(@RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 请假
    @PostMapping("/process_leave/startProcess")
    public R applyeLave(@RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 驳回
    @PutMapping("/approvals/{id}/reject")
    public R approvalsReject(@PathVariable String id, @RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 删除
    @DeleteMapping("/approvals/{id}")
    public R approvalsDel(@PathVariable String id) {
        return R.success(null);
    }

    // 同意
    @PutMapping("/process/instance/commit")
    public R approvalsPass(@RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 离职
    @PostMapping("/process_dimission/startProcess")
    public R applyDimission(@RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 加班
    @PostMapping("/process_overtime/startProcess")
    public R applyOvertime(@RequestBody Map<String, Object> data) {
        return R.success(null);
    }
}
package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.util.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/approvals")
public class ApprovalController {

    // 获取审批历史
    @GetMapping("/flows/{id}")
    public R getReviewHistory(@PathVariable String id) {
        return R.success(null);
    }

    // 处理审批
    @PostMapping("/process")
    public R process(@RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 获取审批状态
    @GetMapping("/setting")
    public R getSetState(@RequestParam Map<String, Object> params) {
        return R.success(null);
    }

    // 保存状态
    @PutMapping("/setting")
    public R saveSetState(@RequestBody Map<String, Object> data) {
        return R.success(null);
    }

    // 导出审批
    @GetMapping("/export/{month}")
    public R exportApprovals(@PathVariable String month) {
        return R.success(null);
    }

    @PostMapping("/export/{month}")
    public R exportApprovalsPost(@PathVariable String month, @RequestBody Map<String, Object> data) {
        return R.success(null);
    }
}
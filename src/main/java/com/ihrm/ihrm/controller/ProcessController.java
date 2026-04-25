package com.ihrm.ihrm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.ihrm.entity.ProcessInstance;
import com.ihrm.ihrm.service.ProcessInstanceService;
import com.ihrm.ihrm.VO.ProcessInstanceVO;
import com.ihrm.ihrm.VO.ProcessPageResult;
import com.ihrm.ihrm.VO.ProcessTaskDetailVO;
import com.ihrm.ihrm.util.R;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/user/process/instance")
public class ProcessController {

    @Resource
    private ProcessInstanceService processInstanceService;

    /**
     * 查询审批申请列表
     */
    @GetMapping("/{page}/{pageSize}")
    public R getProcessList(
            @PathVariable String page,
            @PathVariable String pageSize,
            @RequestHeader(value = "Authorization", required = false) String token) {

        long p = Long.parseLong(page);
        long size = Long.parseLong(pageSize);

        ProcessPageResult result = new ProcessPageResult();
        List<ProcessInstanceVO> pageList = new ArrayList<>();
        
        // TODO: 从数据库查询审批申请列表
        // 示例: pageList = processInstanceService.getProcessList(p, size);
        
        result.setRows(pageList);
        result.setTotal(0);
        result.setCurrApproveCount(0);
        result.setApproveCount(0);
        result.setRejectionCount(0);

        return R.success(result);
    }

    /**
     * 查询审批单详情数据
     */
    @GetMapping("/{id}")
    public R getProcessDetail(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        ProcessInstanceVO vo = new ProcessInstanceVO();
        // TODO: 从数据库查询审批单详情
        // 示例: vo = processInstanceService.getProcessDetail(id);

        return R.success(vo);
    }

    /**
     * 查询审批单审批明细
     */
    @GetMapping("/tasks/detail/{id}")
    public R getProcessTaskDetail(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        List<ProcessTaskDetailVO> list = new ArrayList<>();
        // TODO: 从数据库查询审批单审批明细
        // 示例: list = processInstanceService.getProcessTaskDetail(id);

        return R.success(list);
    }
}
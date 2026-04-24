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

    // 如果你没有这个service，先用假数据调试
    // @Resource
    // private ProcessInstanceService processInstanceService;

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

        // ==================== 模拟数据（完全匹配你的示例） ====================
        ProcessPageResult result = new ProcessPageResult();
        List<ProcessInstanceVO> mockList = getMockData();

        // 分页截取
        int start = (int) ((p - 1) * size);
        int end = Math.min(start + (int) size, mockList.size());
        List<ProcessInstanceVO> pageList = mockList.subList(start, end);

        result.setRows(pageList);
        result.setTotal(mockList.size());
        result.setCurrApproveCount(2);
        result.setApproveCount(3);
        result.setRejectionCount(0);

        return R.success(result);
    }

    // ==================== 完整模拟数据 ====================
    private List<ProcessInstanceVO> getMockData() {
        ProcessInstanceVO vo1 = new ProcessInstanceVO();
        vo1.setDepartmentId("1175311466846683136");
        vo1.setDepartmentName("市场部");
        vo1.setProcApplyTime(new Date(1569236187000L));
        vo1.setProcCurrNodeUserId("107163276022281011210742388013307043841074238801402007552");
        vo1.setProcCurrNodeUserName("文吉星 巴思慧");
        vo1.setProcData("{\"duration\":\"1\",\"reason\":\"个人原因\",\"holidayType\":\"1\",\"processName\":\"请假\",\"processKey\":\"process_leave\",\"startTime\":\"2019-09-16 00:00:00\",\"applyUnit\":\"按天\",\"endTime\":\"2019-09-17 00:00:00\",\"userId\":\"1066370498633486336\"}");
        vo1.setProcessId("1175967133311934464");
        vo1.setProcessKey("process_leave");
        vo1.setProcessName("请假");
        vo1.setProcessState(2);
        vo1.setTimeOfEntry(new Date(1541318400000L));
        vo1.setUserId("1066370498633486336");
        vo1.setUsername("孙财");

        ProcessInstanceVO vo2 = new ProcessInstanceVO();
        vo2.setDepartmentId("1175311466846683136");
        vo2.setDepartmentName("市场部");
        vo2.setProcApplyTime(new Date(1569236417000L));
        vo2.setProcCurrNodeUserId("10753831331064258561075383135371350016");
        vo2.setProcCurrNodeUserName("乔海 董昊空");
        vo2.setProcData("{\"duration\":\"5\",\"reason\":\"没有原因\",\"holidayType\":\"1\",\"processName\":\"请假\",\"processKey\":\"process_leave\",\"startTime\":\"2019-09-15 00:00:00\",\"applyUnit\":\"按天\",\"endTime\":\"2019-09-19 00:00:00\",\"userId\":\"1066370498633486336\"}");
        vo2.setProcessId("1175968095611101184");
        vo2.setProcessKey("process_leave");
        vo2.setProcessName("请假");
        vo2.setProcessState(2);
        vo2.setTimeOfEntry(new Date(1541318400000L));
        vo2.setUserId("1066370498633486336");
        vo2.setUsername("孙财");

        return List.of(vo1, vo2);
    }

    /**
     * 查询审批单详情数据
     */
    @GetMapping("/{id}")
    public R getProcessDetail(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 模拟返回详情数据（完全匹配示例）
        ProcessInstanceVO vo = new ProcessInstanceVO();
        vo.setDepartmentId("1175311466846683136");
        vo.setDepartmentName("市场部");
        vo.setProcApplyTime(new Date(1569236187000L));
        vo.setProcCurrNodeUserId("107163276022281011210742388013307043841074238801402007552");
        vo.setProcCurrNodeUserName("文吉星 巴思慧");
        vo.setProcData("{\"duration\":\"1\",\"reason\":\"个人原因\",\"holidayType\":\"1\",\"processName\":\"请假\",\"processKey\":\"process_leave\",\"startTime\":\"2019-09-16 00:00:00\",\"applyUnit\":\"按天\",\"endTime\":\"2019-09-17 00:00:00\",\"userId\":\"1066370498633486336\"}");
        vo.setProcessId(id);
        vo.setProcessKey("process_leave");
        vo.setProcessName("请假");
        vo.setProcessState(2);
        vo.setTimeOfEntry(new Date(1541318400000L));
        vo.setUserId("1066370498633486336");
        vo.setUsername("孙财");

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

        // 1. 发起申请
        ProcessTaskDetailVO vo1 = new ProcessTaskDetailVO();
        vo1.setHandleOpinion("发起申请");
        vo1.setHandleTime(1569236187000L);
        vo1.setHandleType("2");
        vo1.setHandleUserId("1066370498633486336");
        vo1.setHandleUserName("zbz");
        vo1.setProcessId(id);
        vo1.setTaskId("1175967133987217408");
        vo1.setTaskKey("APPLY");
        vo1.setTaskName("提交申请");
        list.add(vo1);

        // 2. 一级审批通过
        ProcessTaskDetailVO vo2 = new ProcessTaskDetailVO();
        vo2.setHandleOpinion("审批通过");
        vo2.setHandleTime(1569236250000L);
        vo2.setHandleType("2");
        vo2.setHandleUserId("1063705989926227968");
        vo2.setHandleUserName("itcast");
        vo2.setProcessId(id);
        vo2.setTaskId("1175967395195887616");
        list.add(vo2);

        // 3. 二级审批通过
        ProcessTaskDetailVO vo3 = new ProcessTaskDetailVO();
        vo3.setHandleOpinion("通过");
        vo3.setHandleTime(1569236369000L);
        vo3.setHandleType("2");
        vo3.setHandleUserId("1074238801330704384");
        vo3.setHandleUserName("a01");
        vo3.setProcessId(id);
        vo3.setTaskId("1175967897082109952");
        list.add(vo3);

        return R.success(list);
    }
}
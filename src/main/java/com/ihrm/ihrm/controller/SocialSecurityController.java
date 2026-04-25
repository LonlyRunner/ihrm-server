package com.ihrm.ihrm.controller;

import com.alibaba.excel.EasyExcel;
import com.ihrm.ihrm.dto.SocialSecurityQueryDTO;
import com.ihrm.ihrm.dto.SocialSecurityUpdateDTO;
import com.ihrm.ihrm.VO.*;
import com.ihrm.ihrm.service.SocialSecurityService;
import com.ihrm.ihrm.util.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 社保管理
 */
@RestController
@RequestMapping("/social_securitys")
public class SocialSecurityController {

    @Resource
    private SocialSecurityService socialSecurityService;

    /**
     * 查询企业用户社保列表
     */
    @PostMapping("/list")
    public R getSocialSecurityList(
            @RequestBody(required = false) SocialSecurityQueryDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token) {

        try {
            if (dto == null) {
                dto = new SocialSecurityQueryDTO();
            }
            
            List<SocialSecurityVO> allRows = socialSecurityService.getSocialSecurityList(dto);
            
            int total = allRows.size();
            int page = dto.getPage() != null ? dto.getPage() : 1;
            int pageSize = dto.getPageSize() != null ? dto.getPageSize() : 10;
            
            int fromIndex = (page - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, total);
            
            List<SocialSecurityVO> rows = new ArrayList<>();
            if (fromIndex < total) {
                rows = allRows.subList(fromIndex, toIndex);
            }
            
            PageResultVO pageResult = new PageResultVO(total, rows);
            return R.success(pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail("获取社保列表失败: " + e.getMessage());
        }
    }

    /**
     * 查询企业社保配置信息
     */
    @GetMapping("/settings")
    public R getSocialSecuritySetting(
            @RequestHeader(value = "Authorization", required = false) String token) {
        SocialSecuritySettingVO vo = new SocialSecuritySettingVO();
        // TODO: 从数据库查询企业社保配置信息
        // 示例: vo = socialSecurityService.getSocialSecuritySetting();
        return R.success(vo);
    }

    /**
     * 根据用户id查询用户的社保数据
     */
    @GetMapping("/{id}")
    public R getUserSecurityInfo(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        UserSecurityDetailVO detail = new UserSecurityDetailVO();
        // TODO: 从数据库查询用户社保数据
        // 示例: detail = socialSecurityService.getUserSecurityInfo(id);

        return R.success(detail);
    }

    /**
     * 根据城市id查询参保城市的参保项目
     */
    @GetMapping("/payment_item/{id}")
    public R getSecurityPaymentItem(
            @PathVariable String id,
            @RequestHeader(value = "Authorization", required = false) String token) {

        List<SocialSecurityItemVO> list = new ArrayList<>();
        // TODO: 从数据库查询参保城市的参保项目
        // 示例: list = socialSecurityService.getSecurityPaymentItem(id);

        return R.success(list);
    }

    /**
     * 保存或更新用户社保数据
     */
    @PutMapping("/{id}")
    public R saveOrUpdateUserSocialSecurity(
            @PathVariable String id,
            @RequestBody SocialSecurityUpdateDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token) {

        // 业务：根据 userId 保存/更新社保信息
        // TODO: 实现保存或更新用户社保数据
        // 示例: socialSecurityService.saveOrUpdateUserSocialSecurity(id, dto);
        return R.success(null);
    }

    /**
     * 查询当月社保报表
     * GET /social_securitys/historys/{yearMonth}
     */
    @GetMapping("/historys/{yearMonth}")
    public R<List<SecurityMonthReportVO>> getSecurityMonthReport(
            @PathVariable String yearMonth,
            @RequestParam String opType,
            @RequestHeader(value = "Authorization", required = false) String token) {

        List<SecurityMonthReportVO> list = socialSecurityService.getMonthReport(yearMonth, opType);
        return R.success(list);
    }

    /**
     * 制作新报表
     * PUT /social_securitys/historys/{yearMonth}/newReport
     */
    @PutMapping("/historys/{yearMonth}/newReport")
    public R newSocialReport(
            @PathVariable String yearMonth,
            @RequestHeader(value = "Authorization", required = false) String token) {

        socialSecurityService.createNewReport(yearMonth);
        return R.success(null);
    }

    /**
     * 社保数据归档
     * POST /social_securitys/historys/{yearMonth}/archive
     */
    @PostMapping("/historys/{yearMonth}/archive")
    public R archiveSocialData(
            @PathVariable String yearMonth,
            @RequestHeader(value = "Authorization", required = false) String token) {

        socialSecurityService.archive(yearMonth);
        return R.success(null);
    }

    /**
     * 查询社保历史归档列表
     * GET /social_securitys/historys/{year}/list
     */
    @GetMapping("/historys/{year}/list")
    public R<List<SocialSecurityHistoryVO>> getSocialHistoryList(
            @PathVariable String year,
            @RequestHeader(value = "Authorization", required = false) String token) {

        List<SocialSecurityHistoryVO> list = socialSecurityService.getHistoryList(year);
        return R.success(list);
    }

    /**
     * 批量-导出社保月份报表
     * GET /social_securitys/historys/{yearMonth}/export
     */
    @GetMapping("/historys/{yearMonth}/export")
    public void exportSocialReport(
            @PathVariable String yearMonth,
            @RequestParam(required = false) String fileName,
            @RequestParam(required = false, defaultValue = "1") String opType,
            HttpServletResponse response,
            @RequestHeader("Authorization") String token) throws Exception {

        // 1. 调用 service 获取导出数据
        List<SocialSecurityExportVO> list = socialSecurityService.exportData(yearMonth, opType);

        // 2. 设置响应头
        if (fileName == null || fileName.isEmpty()) {
            fileName = "社保报表" + yearMonth;
        }
        String exportFileName = URLEncoder.encode(fileName, "UTF-8") + ".xlsx";
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename*=UTF-8''" + exportFileName);

        // 3. 使用 EasyExcel 写出
        EasyExcel.write(response.getOutputStream(), SocialSecurityExportVO.class)
                .sheet("社保报表")
                .doWrite(list);
    }

    /**
     * 根据用户id和参保年月查询社保归档明细
     * GET /social_securitys/historys/archiveDetail/{userId}/{yearMonth}
     */
    @GetMapping("/historys/archiveDetail/{userId}/{yearMonth}")
    public R<SocialArchiveDetailVO> getArchiveDetail(
            @PathVariable String userId,
            @PathVariable String yearMonth,
            @RequestHeader(value = "Authorization", required = false) String token) {

        SocialArchiveDetailVO detail = socialSecurityService.getArchiveDetail(userId, yearMonth);
        return R.success(detail);
    }
}
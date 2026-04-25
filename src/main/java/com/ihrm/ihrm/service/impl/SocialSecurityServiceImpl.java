package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.VO.*;
import com.ihrm.ihrm.dto.SocialSecurityQueryDTO;
import com.ihrm.ihrm.dto.SocialSecurityUpdateDTO;
import com.ihrm.ihrm.entity.SocialSecurity;
import com.ihrm.ihrm.entity.SysUser;
import com.ihrm.ihrm.mapper.SocialSecurityMapper;
import com.ihrm.ihrm.service.SocialSecurityService;
import com.ihrm.ihrm.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialSecurityServiceImpl extends ServiceImpl<SocialSecurityMapper, SocialSecurity> implements SocialSecurityService {

    @Autowired
    private SysUserService userService;

    @Override
    public void createNewReport(String yearMonth) {
        // ================================
        // 业务逻辑：
        // 1. 根据年月查询用户社保数据
        // 2. 计算企业/个人缴纳金额
        // 3. 批量插入报表表
        // ================================

        // 示例：lambdaQuery()
        //     .eq(SocialSecurity::getSocialSecurityMonth, yearMonth)
        //     .list();
    }

    @Override
    public List<SecurityMonthReportVO> getMonthReport(String yearMonth, String opType) {
        List<SecurityMonthReportVO> list = new ArrayList<>();
        // TODO: 从数据库查询社保月报表数据
        // 示例: list = baseMapper.getMonthReport(yearMonth, opType);
        return list;
    }

    @Override
    public List<SocialSecurityVO> getSocialSecurityList(SocialSecurityQueryDTO dto) {
        try {
            LambdaQueryWrapper<SysUser> userQuery = new LambdaQueryWrapper<>();
            
            userQuery.isNotNull(SysUser::getId);
            
            if (dto.getDepartmentChecks() != null && !dto.getDepartmentChecks().isEmpty()) {
                userQuery.in(SysUser::getDepartmentId, dto.getDepartmentChecks());
            }
            
            List<SysUser> users = userService.list(userQuery);
            
            List<SocialSecurityVO> result = new ArrayList<>();
            
            for (SysUser user : users) {
                LambdaQueryWrapper<SocialSecurity> securityQuery = new LambdaQueryWrapper<>();
                securityQuery.eq(SocialSecurity::getUserId, user.getId());
                
                if (dto.getSocialSecurityChecks() != null && !dto.getSocialSecurityChecks().isEmpty()) {
                    securityQuery.in(SocialSecurity::getParticipatingInTheCityId, dto.getSocialSecurityChecks());
                }
                
                if (dto.getProvidentFundChecks() != null && !dto.getProvidentFundChecks().isEmpty()) {
                    securityQuery.in(SocialSecurity::getProvidentFundCityId, dto.getProvidentFundChecks());
                }
                
                List<SocialSecurity> securities = this.list(securityQuery);
                
                if (securities != null && !securities.isEmpty()) {
                    for (SocialSecurity security : securities) {
                        SocialSecurityVO vo = convertToVO(security, user);
                        result.add(vo);
                    }
                } else {
                    SocialSecurityVO vo = new SocialSecurityVO();
                    vo.setUsername(user.getUsername());
                    vo.setMobile(user.getMobile());
                    vo.setWorkNumber(user.getWorkNumber());
                    vo.setDepartmentName(user.getDepartmentName());
                    
                    if (user.getTimeOfEntry() != null) {
                        vo.setTimeOfEntry(user.getTimeOfEntry().toString());
                    }
                    
                    result.add(vo);
                }
            }
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("查询社保列表失败: " + e.getMessage());
        }
    }
    
    private SocialSecurityVO convertToVO(SocialSecurity security, SysUser user) {
        SocialSecurityVO vo = new SocialSecurityVO();
        vo.setId(security.getId());
        vo.setUsername(security.getUsername() != null ? security.getUsername() : user.getUsername());
        vo.setMobile(security.getMobile() != null ? security.getMobile() : user.getMobile());
        vo.setWorkNumber(user.getWorkNumber());
        vo.setDepartmentName(user.getDepartmentName());
        
        if (user.getTimeOfEntry() != null) {
            vo.setTimeOfEntry(user.getTimeOfEntry().toString());
        }
        
        vo.setParticipatingInTheCityId(security.getParticipatingInTheCityId());
        vo.setParticipatingInTheCity(security.getParticipatingInTheCity() != null ? security.getParticipatingInTheCity() : "未设置");
        vo.setSocialSecurityBase(security.getSocialSecurityBase() != null ? security.getSocialSecurityBase() : 0);
        
        vo.setProvidentFundCityId(security.getProvidentFundCityId());
        vo.setProvidentFundCity(security.getProvidentFundCity() != null ? security.getProvidentFundCity() : "未设置");
        vo.setProvidentFundBase(security.getProvidentFundBase() != null ? security.getProvidentFundBase() : 0);
        
        return vo;
    }

    @Override
    public SocialSecuritySettingVO getSocialSecuritySetting() {
        SocialSecuritySettingVO vo = new SocialSecuritySettingVO();
        // TODO: 从数据库查询企业社保配置信息
        // 示例: vo = baseMapper.getSocialSecuritySetting();
        return vo;
    }

    @Override
    public UserSecurityDetailVO getUserSecurityInfo(String id) {
        UserSecurityDetailVO detail = new UserSecurityDetailVO();
        // TODO: 从数据库查询用户社保数据
        // 示例: detail = baseMapper.getUserSecurityInfo(id);
        return detail;
    }

    @Override
    public List<SocialSecurityItemVO> getSecurityPaymentItem(String id) {
        List<SocialSecurityItemVO> list = new ArrayList<>();
        // TODO: 从数据库查询参保城市的参保项目
        // 示例: list = baseMapper.getSecurityPaymentItem(id);
        return list;
    }

    @Override
    public void saveOrUpdateUserSocialSecurity(String id, SocialSecurityUpdateDTO dto) {
        // TODO: 保存或更新用户社保数据
        // 示例：
        // SocialSecurity security = new SocialSecurity();
        // security.setUserId(id);
        // // 设置其他字段...
        // saveOrUpdate(security);
    }

    @Override
    public void archive(String yearMonth) {
        // TODO: 执行社保数据归档
        // 业务逻辑：
        // 1. 根据年月查询社保报表数据
        // 2. 将数据归档到历史表
    }

    @Override
    public List<SocialSecurityHistoryVO> getHistoryList(String year) {
        List<SocialSecurityHistoryVO> list = new ArrayList<>();
        // TODO: 从数据库查询社保历史归档列表
        // 示例: list = baseMapper.getHistoryList(year);
        return list;
    }

    @Override
    public List<SocialSecurityExportVO> exportData(String yearMonth, String opType) {
        List<SocialSecurityExportVO> list = new ArrayList<>();
        // TODO: 从数据库查询需要导出的社保数据
        // 示例: list = baseMapper.exportData(yearMonth, opType);
        return list;
    }

    @Override
    public SocialArchiveDetailVO getArchiveDetail(String userId, String yearMonth) {
        SocialArchiveDetailVO detail = new SocialArchiveDetailVO();
        // TODO: 从数据库查询社保归档明细
        // 示例: detail = baseMapper.getArchiveDetail(userId, yearMonth);
        return detail;
    }
}
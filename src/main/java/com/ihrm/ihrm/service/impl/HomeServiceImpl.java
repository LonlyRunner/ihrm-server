package com.ihrm.ihrm.service.impl;

import com.ihrm.ihrm.VO.DeclarationDataVO;
import com.ihrm.ihrm.VO.HomeDataVO;
import com.ihrm.ihrm.VO.HomeNoticeVO;
import com.ihrm.ihrm.service.HomeService;
import com.ihrm.ihrm.mapper.SysUserMapper;
import com.ihrm.ihrm.mapper.SocialSecurityMapper;
import com.ihrm.ihrm.mapper.NoticeMapper;
import com.ihrm.ihrm.entity.SysUser;
import com.ihrm.ihrm.entity.SocialSecurity;
import com.ihrm.ihrm.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SocialSecurityMapper socialSecurityMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public HomeDataVO getHomeData() {
        HomeDataVO vo = new HomeDataVO();

        try {
            // ================= 基础统计（使用MP查询） =================
            // 总人数
            Long count = sysUserMapper.selectCount(null);
            vo.setEmployeeTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 正式员工数（假设formOfEmployment=1表示正式员工）
            count = sysUserMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SysUser>()
                            .eq("form_of_employment", 1)
            );
            vo.setRegularEmployeeTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 合同待签署总数（假设state=0表示待签署）
            count = sysUserMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SysUser>()
                            .eq("state", 0)
            );
            vo.setContractSignTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 待入职人数（假设state=1表示待入职）
            count = sysUserMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SysUser>()
                            .eq("state", 1)
            );
            vo.setToBeEmployed(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 本月待转正人数（假设correctionTime在本月）
            count = sysUserMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SysUser>()
                            .between("correction_time", 
                                    java.time.LocalDateTime.now().withDayOfMonth(1),
                                    java.time.LocalDateTime.now().withDayOfMonth(
                                            java.time.LocalDate.now().lengthOfMonth()
                                    )
                            )
            );
            vo.setToBeConfirmed(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 本月待离职人数（暂时设为0，需要根据实际业务逻辑调整）
            vo.setToBeDismissed(0);
            
            // 接口访问量（暂时设为0，需要日志表支持）
            vo.setInterfaceAccessTotal(0);

            // ================= 社保数据 =================
            DeclarationDataVO social = new DeclarationDataVO();
            social.setCategory("社保");
            social.setCategoryType("social");
            
            // 总申报（社保缴费人数）
            count = socialSecurityMapper.selectCount(null);
            social.setDeclarationTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 待申报（假设socialSecurityType=0表示待申报）
            count = socialSecurityMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SocialSecurity>()
                            .eq("social_security_type", 0)
            );
            social.setToDeclareTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 申报中（假设socialSecurityType=1表示申报中）
            count = socialSecurityMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SocialSecurity>()
                            .eq("social_security_type", 1)
            );
            social.setDeclaringTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 已申报（假设socialSecurityType=2表示已申报）
            count = socialSecurityMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SocialSecurity>()
                            .eq("social_security_type", 2)
            );
            social.setDeclaredTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            social.setXAxis(Arrays.asList("1月","2月","3月","4月","5月","6月"));
            social.setYAxis(Arrays.asList(10,20,30,40,50,60));
            vo.setSocialInsurance(social);

            // ================= 公积金数据 =================
            DeclarationDataVO fund = new DeclarationDataVO();
            fund.setCategory("公积金");
            fund.setCategoryType("fund");
            
            // 总申报（公积金缴费人数）
            count = socialSecurityMapper.selectCount(null);
            fund.setDeclarationTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 待申报（假设providentFundBase=0表示待申报）
            count = socialSecurityMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SocialSecurity>()
                            .eq("provident_fund_base", 0)
            );
            fund.setToDeclareTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 申报中（假设providentFundBase>0且未归档）
            count = socialSecurityMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SocialSecurity>()
                            .gt("provident_fund_base", 0)
            );
            fund.setDeclaringTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            // 已申报（暂时使用相同数据）
            count = socialSecurityMapper.selectCount(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<SocialSecurity>()
                            .gt("provident_fund_base", 0)
            );
            fund.setDeclaredTotal(count != null && count <= Integer.MAX_VALUE ? count.intValue() : 0);
            
            fund.setXAxis(Arrays.asList("1月","2月","3月","4月","5月","6月"));
            fund.setYAxis(Arrays.asList(8,18,28,38,48,58));
            vo.setProvidentFund(fund);

        } catch (Exception e) {
            // 记录异常信息
            e.printStackTrace();
            // 设置默认值
            if (vo.getEmployeeTotal() == null) vo.setEmployeeTotal(0);
            if (vo.getRegularEmployeeTotal() == null) vo.setRegularEmployeeTotal(0);
            if (vo.getContractSignTotal() == null) vo.setContractSignTotal(0);
            if (vo.getToBeEmployed() == null) vo.setToBeEmployed(0);
            if (vo.getToBeConfirmed() == null) vo.setToBeConfirmed(0);
            if (vo.getToBeDismissed() == null) vo.setToBeDismissed(0);
            if (vo.getInterfaceAccessTotal() == null) vo.setInterfaceAccessTotal(0);
        }

        return vo;
    }

    @Override
    public List<HomeNoticeVO> getNoticeList() {
        try {
            // 查询最新的10条通知公告，按创建时间降序排列
            return noticeMapper.selectList(
                    new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Notice>()
                            .orderByDesc("create_time")
                            .last("LIMIT 10")
            ).stream().map(notice -> {
                HomeNoticeVO vo = new HomeNoticeVO();
                // 优先使用数据库中的图片/图标地址，如果没有则使用默认图标
                if (notice.getImageUrl() != null && !notice.getImageUrl().isEmpty()) {
                    vo.setIcon(notice.getImageUrl());
                } else {
                    vo.setIcon("bell");
                }
                // 使用内容作为通知内容
                vo.setNotice(notice.getContent());
                // 格式化创建时间
                if (notice.getCreateTime() != null) {
                    vo.setCreateTime(notice.getCreateTime().toString().replace("T", " "));
                }
                return vo;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
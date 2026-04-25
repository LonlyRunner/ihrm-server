package com.ihrm.ihrm.service.impl;

import com.ihrm.ihrm.VO.DeclarationDataVO;
import com.ihrm.ihrm.VO.HomeDataVO;
import com.ihrm.ihrm.VO.HomeNoticeVO;
import com.ihrm.ihrm.service.HomeService;
import com.ihrm.ihrm.mapper.UserMapper;
import com.ihrm.ihrm.mapper.SocialSecurityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SocialSecurityMapper socialSecurityMapper;

    @Override
    public HomeDataVO getHomeData() {
        HomeDataVO vo = new HomeDataVO();

        // ================= 基础统计（你只需补全MP查询） =================
        vo.setEmployeeTotal(0);         // 总人数
        vo.setRegularEmployeeTotal(0);  // 正式员工
        vo.setContractSignTotal(0);     // 待签合同
        vo.setToBeEmployed(0);          // 待入职
        vo.setToBeConfirmed(0);         // 待转正
        vo.setToBeDismissed(0);         // 待离职
        vo.setInterfaceAccessTotal(0); // 接口访问量

        // ================= 社保数据 =================
        DeclarationDataVO social = new DeclarationDataVO();
        social.setCategory("社保");
        social.setCategoryType("social");
        social.setDeclarationTotal(0);    // 总申报
        social.setToDeclareTotal(0);      // 待申报
        social.setDeclaringTotal(0);      // 申报中
        social.setDeclaredTotal(0);       // 已申报
        social.setXAxis(Arrays.asList("1月","2月","3月","4月","5月","6月"));
        social.setYAxis(Arrays.asList(10,20,30,40,50,60));
        vo.setSocialInsurance(social);

        // ================= 公积金数据 =================
        DeclarationDataVO fund = new DeclarationDataVO();
        fund.setCategory("公积金");
        fund.setCategoryType("fund");
        fund.setDeclarationTotal(0);
        fund.setToDeclareTotal(0);
        fund.setDeclaringTotal(0);
        fund.setDeclaredTotal(0);
        fund.setXAxis(Arrays.asList("1月","2月","3月","4月","5月","6月"));
        fund.setYAxis(Arrays.asList(8,18,28,38,48,58));
        vo.setProvidentFund(fund);

        return vo;
    }

    @Override
    public List<HomeNoticeVO> getNoticeList() {
        // ================================
        // 业务：查询系统消息/通知
        // 来源：sys_notice 或 user_notice 表
        // ================================

        // 示例（你替换成 MP 查询）
        // return noticeService.lambdaQuery()
        //         .orderByDesc(Notice::getCreateTime)
        //         .last("LIMIT 10")
        //         .list()
        //         .stream().map(n -> {
        //             HomeNoticeVO vo = new HomeNoticeVO();
        //             vo.setIcon("bell");
        //             vo.setNotice(n.getContent());
        //             vo.setCreateTime(n.getCreateTime());
        //             return vo;
        //         }).collect(Collectors.toList());

        return new ArrayList<>();
    }
}
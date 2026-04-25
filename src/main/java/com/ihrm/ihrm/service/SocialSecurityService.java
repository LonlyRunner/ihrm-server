package com.ihrm.ihrm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihrm.ihrm.VO.*;
import com.ihrm.ihrm.dto.SocialSecurityQueryDTO;
import com.ihrm.ihrm.dto.SocialSecurityUpdateDTO;
import com.ihrm.ihrm.entity.SocialSecurity;

import java.util.List;

public interface SocialSecurityService extends IService<SocialSecurity> {

    void createNewReport(String yearMonth);

    List<SecurityMonthReportVO> getMonthReport(String yearMonth, String opType);

    List<SocialSecurityVO> getSocialSecurityList(SocialSecurityQueryDTO dto);

    SocialSecuritySettingVO getSocialSecuritySetting();

    UserSecurityDetailVO getUserSecurityInfo(String id);

    List<SocialSecurityItemVO> getSecurityPaymentItem(String id);

    void saveOrUpdateUserSocialSecurity(String id, SocialSecurityUpdateDTO dto);

    void archive(String yearMonth);

    List<SocialSecurityHistoryVO> getHistoryList(String year);

    List<SocialSecurityExportVO> exportData(String yearMonth, String opType);

    SocialArchiveDetailVO getArchiveDetail(String userId, String yearMonth);
}
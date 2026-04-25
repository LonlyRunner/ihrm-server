package com.ihrm.ihrm.VO;

import lombok.Data;

@Data
public class HomeNoticeVO {
    private String icon;        // 图标
    private String notice;      // 通知内容
    private String createTime;  // 创建时间
}
package com.ihrm.ihrm.service;

import com.ihrm.ihrm.VO.HomeDataVO;
import com.ihrm.ihrm.VO.HomeNoticeVO;

import java.util.List;

public interface HomeService {
    HomeDataVO getHomeData();
    List<HomeNoticeVO> getNoticeList();
}
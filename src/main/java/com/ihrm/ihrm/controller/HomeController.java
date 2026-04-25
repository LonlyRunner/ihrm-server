package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.VO.HomeDataVO;
import com.ihrm.ihrm.VO.HomeNoticeVO;
import com.ihrm.ihrm.service.HomeService;
import com.ihrm.ihrm.util.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Resource
    private HomeService homeService;

    @GetMapping("/home/data")
    public R<HomeDataVO> getHomeData(
            @RequestHeader(value = "Authorization", required = false) String token) {

        HomeDataVO data = homeService.getHomeData();
        return R.success(data);
    }

    @GetMapping("/home/notice")
    public R<List<HomeNoticeVO>> getHomeNotice(
            @RequestHeader(value = "Authorization", required = false) String token) {

        List<HomeNoticeVO> list = homeService.getNoticeList();
        return R.success(list);
    }
}
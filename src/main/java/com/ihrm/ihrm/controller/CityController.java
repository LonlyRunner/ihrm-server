package com.ihrm.ihrm.controller;

import com.ihrm.ihrm.VO.CityVO;
import com.ihrm.ihrm.service.CityService;
import com.ihrm.ihrm.util.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    @Resource
    private CityService cityService;

    @GetMapping("/sys/city")
    public R<List<CityVO>> getCityList(
            @RequestHeader("Authorization") String token) {
        List<CityVO> cityList = cityService.getCityList();
        return R.success(cityList);
    }
}
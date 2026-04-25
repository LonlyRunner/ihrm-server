package com.ihrm.ihrm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ihrm.ihrm.VO.CityVO;
import com.ihrm.ihrm.entity.City;

import java.util.List;

public interface CityService extends IService<City> {

    List<CityVO> getCityList();
}
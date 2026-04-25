package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.VO.CityVO;
import com.ihrm.ihrm.entity.City;
import com.ihrm.ihrm.mapper.CityMapper;
import com.ihrm.ihrm.service.CityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Override
    public List<CityVO> getCityList() {
        List<CityVO> list = new ArrayList<>();
        // TODO: 从数据库查询城市列表
        // 示例: list = baseMapper.getCityList();
        return list;
    }
}
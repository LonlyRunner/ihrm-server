package com.ihrm.ihrm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ihrm.ihrm.entity.AttendanceMonthlyReport;
import com.ihrm.ihrm.mapper.AttendanceMonthlyReportMapper;
import com.ihrm.ihrm.service.AttendanceMonthlyReportService;
import org.springframework.stereotype.Service;

@Service
public class AttendanceMonthlyReportServiceImpl extends ServiceImpl<AttendanceMonthlyReportMapper, AttendanceMonthlyReport> implements AttendanceMonthlyReportService {
}
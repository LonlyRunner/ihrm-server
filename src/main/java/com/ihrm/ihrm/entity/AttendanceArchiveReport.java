package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("atte_archive_report")
public class AttendanceArchiveReport extends BaseEntity {
    private String companyId;
    private String departmentId;
    private String archiveYear;
    private String archiveMonth;
    private Integer totalPeopleNum;
    private Integer fullAttePeopleNum;
    private Integer isArchived;
}
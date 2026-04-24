package com.ihrm.ihrm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("process_instance")
public class ProcessInstance extends BaseEntity {
    private String processId;
    private String processKey;
    private String processName;
    private Integer processState;
    private String userId;
    private String username;
    private String departmentId;
    private String departmentName;
    private Date procApplyTime;
    private String procCurrNodeUserId;
    private String procCurrNodeUserName;
    private String procData;
    private Date timeOfEntry;
}
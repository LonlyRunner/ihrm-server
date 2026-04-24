package com.ihrm.ihrm.VO;

import lombok.Data;
import java.util.Date;

@Data
public class ProcessInstanceVO {
    private String departmentId;
    private String departmentName;
    private Date procApplyTime;
    private String procCurrNodeUserId;
    private String procCurrNodeUserName;
    private String procData;
    private String processId;
    private String processKey;
    private String processName;
    private Integer processState;
    private Date timeOfEntry;
    private String userId;
    private String username;
}
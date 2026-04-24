package com.ihrm.ihrm.VO;

import lombok.Data;

@Data
public class ProcessTaskDetailVO {
    private String handleOpinion;
    private Long handleTime;
    private String handleType;
    private String handleUserId;
    private String handleUserName;
    private String processId;
    private String taskId;
    private String taskKey;
    private String taskName;
}
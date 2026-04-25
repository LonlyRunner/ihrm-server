package com.ihrm.ihrm.VO;

import lombok.Data;
import java.util.List;

@Data
public class UserInfoVO {
    private String id;
    private String mobile;
    private String username;
    private String password;
    private Integer enableState;
    private String createTime;
    private String companyId;
    private String companyName;
    private String departmentId;
    private String timeOfEntry;
    private Integer formOfEmployment;
    private String workNumber;
    private String formOfManagement;
    private String workingCity;
    private String correctionTime;
    private Integer inServiceStatus;
    private String departmentName;
    private List<String> roleIds;
    private String staffPhoto;
}
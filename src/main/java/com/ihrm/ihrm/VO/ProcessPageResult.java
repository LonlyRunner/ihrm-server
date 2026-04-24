package com.ihrm.ihrm.VO;

import lombok.Data;
import java.util.List;

@Data
public class ProcessPageResult {
    private List<ProcessInstanceVO> rows;
    private long total;
    private int currApproveCount;
    private int approveCount;
    private int rejectionCount;
}
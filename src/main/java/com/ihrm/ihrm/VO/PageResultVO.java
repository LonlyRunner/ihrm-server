package com.ihrm.ihrm.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResultVO {
    private long total;
    private List<?> rows;
}
package com.ihrm.ihrm.VO;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CityVO {
    private String id;
    private String name;
    private LocalDateTime createTime;
}
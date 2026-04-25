package com.ihrm.ihrm.VO;

import lombok.Data;
import java.util.List;

@Data
public class DeclarationDataVO {
    private String category;
    private String categoryType;
    private Integer declarationTotal;
    private Integer toDeclareTotal;
    private Integer declaringTotal;
    private Integer declaredTotal;
    private List<String> xAxis;
    private List<Integer> yAxis;
}
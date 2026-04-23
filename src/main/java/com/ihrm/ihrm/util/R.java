package com.ihrm.ihrm.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "通用返回结果")
public class R<T> {

    @Schema(description = "响应消息")
    private String message;

    @Schema(description = "是否成功")
    private boolean success;

    @Schema(description = "业务状态码，10000成功")
    private Integer code;

    @Schema(description = "返回数据")
    private T data;

    // 成功（带数据）
    public static <T> R<T> success(T data) {
        R<T> r = new R<>();
        r.setMessage("操作成功");
        r.setSuccess(true);
        r.setCode(10000);
        r.setData(data);
        return r;
    }

    // 成功（带消息）
    public static <T> R<T> success(String message) {
        R<T> r = new R<>();
        r.setMessage(message);
        r.setSuccess(true);
        r.setCode(10000);
        r.setData(null);
        return r;
    }

    // 失败
    public static <T> R<T> fail(String message) {
        R<T> r = new R<>();
        r.setMessage(message);
        r.setSuccess(false);
        r.setCode(50000);
        r.setData(null);
        return r;
    }
}
package com.mickey.tech.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 服务器异常
     */
    SERVER_ERROR(500, "服务器异常"),

    /**
     * 参数不合法
     */
    ILLEGAL_PARAMETERS(40000, "参数不合法");

    private Integer code;
    private String msg;

}

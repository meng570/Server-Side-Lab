package com.stu.helloserver.common;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    USER_EXIST(4001, "用户名已存在"),
    USER_NOT_EXIST(4002, "用户不存在"),
    PASSWORD_ERROR(4003, "密码错误"),
    PARAM_ERROR(4004, "参数错误"),
    UNAUTHORIZED(401, "未登录或登录已过期");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
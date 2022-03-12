package com.white.domain;

public enum ResultInfo {
    LOGIN_SUCCESS("201","登陆成功"),
    LOGIN_FAIL("401","登陆失败"),
    ACCESS_DENY("403","权限不足"),
    NOT_FOUND("404", "没有找到"),
    SUCCESS("200", "操作成功"),
    GLOBAL_ERROR("101","系统繁忙"),
    ;

    private String code;
    private String message;

    ResultInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

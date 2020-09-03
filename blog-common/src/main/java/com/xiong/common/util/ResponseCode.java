package com.xiong.common.util;


import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 封装响应状态码
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseCode {

    SUCCESS(200, "操作成功"),
    ERROR(1000, "操作失败"),
    SERVER_ERROR(500, "服务器异常"),

    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    REPETITIVE_OPERATION(1005, "请勿重复操作"),
    ACCESS_LIMIT(1006, "请求太频繁, 请稍后再试"),

    USER_LOGIN_SUCCESS(2000, "登录成功"),
    USER_LOGIN_FAILURE(2001, "登录失败"),
    USER_NOT_LOGIN(2002, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2003, "账号过期，请联系管理员"),
    USER_CREDENTIALS_ERROR(2004, "用户名或者密码错误，请重新输入"),
    USER_CREDENTIALS_EXPIRED(2005, "密码过期，请联系管理员"),
    USER_ACCOUNT_DISABLE(2006, "账户被禁用，请联系管理员"),
    USER_ACCOUNT_LOCKED(2007, "账户被锁定，请联系管理员"),
    USER_ACCOUNT_NOT_EXIST(2008, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2009, "账号已存在"),
    USER_ACCOUNT_LOGOUT_SUCCESS(2010, "账号注销成功"),

    USER_NO_PERMISSION(2011, "没有访问权限"),
    USER_ACCOUNT_BY_OTHERS(2012, "账号在另一台设备登录，如不是本人操作，请修改密码"),
    USER_REQUEST_FAILURE(2013, "请求失败，请联系管理员");

    private Integer code;
    private String msg;

    ResponseCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 根据code获取message
     *
     * @param code
     * @return
     */
    public static String getMsgByCode(Integer code) {
        for (ResponseCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMsg();
            }
        }
        return null;
    }
}

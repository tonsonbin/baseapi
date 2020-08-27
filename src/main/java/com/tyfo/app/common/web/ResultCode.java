package com.tyfo.app.common.web;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
	SUCCESS("200"),//成功
    FAIL("400"),//失败
    UNAUTHORIZED("401"),//未认证（签名错误）
    PERMISSION_DENIED("402"),//接口权限不足
    LOGIN_GETUSERINFO("403"),//需要手动登录-获取用户信息（view表示要登录）
    LOGIN_REFRESHTOKEN("40302"),//需要刷新token-不用获取用户信息
    LOGIN_UNBINDPHONE("40301"),//未绑定手机号
    NOT_FOUND("404"),//接口不存在
    INTERNAL_SERVER_ERROR("500");//服务器内部错误

    private final String code;

    ResultCode(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}

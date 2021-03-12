package com.tb.app.common.web;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {
	/**
	 * 成功
	 */
	SUCCESS("200"),
	/**
	 * 失败
	 */
    FAIL("400"),
    
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED("401"),
    /**
     * 接口权限不足
     */
    PERMISSION_DENIED("402"),
    /**
     * 数据体获取失败
     */
    REQUEST_HEADER_ERROR("40201"),
    /**
     * 未配置应用id
     */
    APPLICATION_NONE("40202"),
    /**
     * 为渠道body数据
     */
    REQUEST_BODY_ERROR("40203"),
    
    /**
     * 需要手动登录-获取用户信息（view表示要登录）
     */
    LOGIN_GETUSERINFO("403"),
    /**
     * 需要刷新token-不用获取用户信息
     */
    LOGIN_REFRESHTOKEN("40302"),
    /**
     * 未绑定手机号
     */
    LOGIN_UNBINDPHONE("40301"),
    /**
     * 接口不存在
     */
    NOT_FOUND("404"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR("500");

    private final String code;

    ResultCode(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}

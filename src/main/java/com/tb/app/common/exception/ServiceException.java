package com.tb.app.common.exception;

import com.tb.app.common.web.ResultCode;

/**
 * 服务（业务）异常如“ 账号或密码错误 ”，该异常只做INFO级别的日志记录 @see WebMvcConfigurer
 */
public class ServiceException extends RuntimeException {

	private String code;
	
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
    
    public ServiceException(String code,String message) {
        super(message);
        this.code = code;
    }
    
    public ServiceException(ResultCode resultCode,String message) {
        super(message);
        this.code = resultCode.code();
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

	public String getCode() {
		return code;
	}
    
}

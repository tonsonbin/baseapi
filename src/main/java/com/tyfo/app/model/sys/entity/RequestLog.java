package com.tyfo.app.model.sys.entity;

import com.tyfo.app.common.persistence.DataEntity;

/**
 * 
 */
public class RequestLog extends DataEntity<RequestLog> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 请求连接
	 */
	private String requestUrl;
	/**
	 * 请求唯一标识
	 */
	private String requestId;
	/**
     * 请求json
     */
    private String requestJson;

    /**
     * 请求类型 1、非通 2、云商 3、电渠
     */
    private String requestType;

    /**
     * 请求类型描述
     */
    private String requestDesc;

    /**
     * 响应码
     */
    private String responseCode;

    /**
     * 响应消息
     */
    private String responseMsg;

    /**
     * 响应json
     */
    private String responseJson;

    /**
     * 错误描述
     */
    private String exception;
    
    /**
     * 访问者ip
     */
    private String ip;
    
    /**
     * 是否进行日志保存，默认不记录日志
     */
    private boolean save = false;
    
    /**
     * 是否是最终出口端，只有处于出口端才会最终触发日志信息入库操作
     */
    private boolean finallyOut = false;

    /**
     * 创建表格的年
     */
    private String year;
    /**
     * 创建表格的月
     */
    private String month;
    
    /**
     * 请求json
     * @return request_json 请求json
     */
    public String getRequestJson() {
        return requestJson;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    /**
     */
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * 请求类型描述
     * @return request_desc 请求类型描述
     */
    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    /**
     * 响应码
     * @return response_code 响应码
     */
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * 响应消息
     * @return response_msg 响应消息
     */
    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    /**
     * 响应json
     * @return response_json 响应json
     */
    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public boolean isFinallyOut() {
		return finallyOut;
	}

	public void setFinallyOut(boolean finallyOut) {
		this.finallyOut = finallyOut;
	}

	
    
}
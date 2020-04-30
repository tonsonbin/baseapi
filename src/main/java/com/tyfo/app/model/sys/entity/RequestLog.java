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
     * 请求json
     */
    private String requestJson;

    /**
     * 请求类型 1、非通 2、云商
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
     * 请求类型 1=订单可售性效验 2=订单提交 3=订单发货 4=订单物流信息推送 5=订单收货
     * @return request_type 请求类型 1=订单可售性效验 2=订单提交 3=订单发货 4=订单物流信息推送 5=订单收货
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
    
}
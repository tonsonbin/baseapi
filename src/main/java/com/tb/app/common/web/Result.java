package com.tb.app.common.web;


import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 统一API响应结果封装
 */
public class Result {
    private String code;
    private String message;
    private Object data;
    
    /**
     * 系统加密信息，便于错误调试
     */
    private String isi;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }
    
    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return StringUtils.isNoneBlank(this.code) && StringUtils.equals(this.code,ResultCode.SUCCESS.code());
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

	public String getIsi() {
		return isi;
	}

	public Result setIsi(String isi) {
		this.isi = isi;
		return this;
	}

	
    
}

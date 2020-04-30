package com.tyfo.app.common.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * 统一服务端封装
 */
public class ResultService {
    private String code;
    private String message;
    private Object data;

    private ResultService() {
    	
    	
    }
    
    public static ResultService error(String message) {
		
    	ResultService resultService = new ResultService();
    	resultService.setCode(ResultServiceCode.FAIL);
    	resultService.setData(Maps.newHashMap());
    	resultService.setMessage(message);
    	
    	return resultService;
    	
	}
    public static ResultService error(String code,String message) {
		
    	ResultService resultService = new ResultService();
    	resultService.setCode(code);
    	resultService.setData(Maps.newHashMap());
    	resultService.setMessage(message);
    	
    	return resultService;
    	
	}
    public static ResultService success(Object data) {
		
    	ResultService resultService = new ResultService();
    	resultService.setCode(ResultServiceCode.SUCCESS);
    	resultService.setData(data);
    	resultService.setMessage("");
    	
    	return resultService;
    	
	}
    public static ResultService success() {
		
    	ResultService resultService = new ResultService();
    	resultService.setCode(ResultServiceCode.SUCCESS);
    	resultService.setData(Maps.newHashMap());
    	resultService.setMessage("");
    	
    	return resultService;
    	
	}
    
    public ResultService setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ResultService setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultService setData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return this.code == ResultServiceCode.SUCCESS;
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
}

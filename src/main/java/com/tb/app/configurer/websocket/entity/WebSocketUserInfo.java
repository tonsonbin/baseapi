package com.tb.app.configurer.websocket.entity;

/**
 * 用户信息实体
 * @author tangbin
 * @date 2021年1月29日
 */
public class WebSocketUserInfo {

	//用户id
	private String userId = "";
	//用户姓名
	private String name = "";
	
	public WebSocketUserInfo() {
		
	}
	
	/**
	 * @param userId 用户id
	 * @param message 用户发送的消息
	 */
	public WebSocketUserInfo(String userId,String name) {
		
		this.userId = userId;
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

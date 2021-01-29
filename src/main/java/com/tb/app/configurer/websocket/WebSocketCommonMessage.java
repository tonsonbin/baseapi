package com.tb.app.configurer.websocket;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.tb.app.configurer.websocket.entity.WebSocketUserInfo;

public class WebSocketCommonMessage {

	static Logger log = LoggerFactory.getLogger(WebSocketCommonMessage.class);
	
	private static final String KEY_SYSMESSAGE = "sysMessage";
	private static final String KEY_SYSMESSAGE_USERONLINE = "sysMessage-userOnline";
	private static final String KEY_USERMESSAGE = "userMessage";
	private static final String KEY_RECEIVEUSERINFO = "receiveUserInfo";
	private static final String KEY_SENDUSERINFO = "sendUserInfo";
	
	private Map<String, Object> message = Maps.newHashMap();
	
	public static WebSocketCommonMessage init(String sid,String channelId) {
		
		WebSocketCommonMessage webSocketCommonMessage = new WebSocketCommonMessage();
		webSocketCommonMessage.setSid(sid);
		webSocketCommonMessage.setChannelId(channelId);
		return webSocketCommonMessage;
		
	}
	
	/**
	 * 消息发送者的sid
	 */
	private String sid = "";
	/**
	 * 消息发送者的sid
	 */
	private String channelId = "";
	
	/**
	 * 获取所有的消息体
	 * @return
	 */
	public Map<String,Object> getMessage(){
		
		return message;
		
	}
	
	/**
	 * 添加sysMessage信息
	 * 系统消息提示信息
	 * @param smessage
	 * @return
	 */
	public WebSocketCommonMessage addSysMessage(String smessage) {
		
		message.put(KEY_SYSMESSAGE, smessage);
		
		return this;
	}
	/**
	 * 获取当前系统的信息
	 * 
	 * @param smessage
	 * @return
	 */
	public String getSysMessage() {
		
		if (message.containsKey(KEY_SYSMESSAGE)) {
			return (String) message.get(KEY_SYSMESSAGE);
		}
		
		return null;
	}
	
	/**
	 * 添加SysMessageUserOnline信息
	 * 用户上下线信息
	 * @param smessage
	 * @return
	 */
	public WebSocketCommonMessage addSysMessageUserOnline(String smessage) {
		
		message.put(KEY_SYSMESSAGE_USERONLINE, smessage);
		
		return this;
	}
	/**
	 * 获取当前SysMessageUserOnline的信息
	 * 
	 * @param smessage
	 * @return
	 */
	public String getSysMessageUserOnline() {
		
		if (message.containsKey(KEY_SYSMESSAGE_USERONLINE)) {
			return (String) message.get(KEY_SYSMESSAGE_USERONLINE);
		}
		
		return null;
	}
	
	/**
	 * 添加发送信息
	 * 
	 * @param smessage
	 * @return
	 */
	public WebSocketCommonMessage addUserMessage(String userMessage) {
		
		message.put(KEY_USERMESSAGE, userMessage);
		
		return this;
	}
	
	/**
	 * 获取发送信息
	 * 
	 * @param smessage
	 * @return
	 */
	public String getUserMessage() {
		
		if (message.containsKey(KEY_USERMESSAGE)) {
			return (String) message.get(KEY_USERMESSAGE);
		}
		
		return null;
	}
	
	/**
	 * 添加接收者信息
	 * 
	 * @param smessage
	 * @return
	 */
	public WebSocketCommonMessage addReceiveUserInfo(WebSocketUserInfo receiveUserInfo) {
		
		message.put(KEY_RECEIVEUSERINFO, receiveUserInfo);
		
		return this;
	}
	
	/**
	 * 获取接收者信息
	 * 
	 * @param smessage
	 * @return
	 */
	public WebSocketUserInfo getReceiveUserInfo() {
		
		if (message.containsKey(KEY_RECEIVEUSERINFO)) {
			return  (WebSocketUserInfo) message.get(KEY_RECEIVEUSERINFO);
		}
		
		return null;
	}
	
	/**
	 * 添加发送者信息
	 * 
	 * @param smessage
	 * @return
	 */
	public WebSocketCommonMessage addSendUserInfo(WebSocketUserInfo webSocketUserInfo) {
		
		message.put(KEY_SENDUSERINFO, webSocketUserInfo);
		
		return this;
	}
	
	/**
	 * 获取发送者信息
	 * 
	 * @param smessage
	 * @return
	 */
	public WebSocketUserInfo getSendUserInfo() {
		
		if (message.containsKey(KEY_SENDUSERINFO)) {
			return  (WebSocketUserInfo) message.get(KEY_SENDUSERINFO);
		}
		
		return null;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	
}

package com.tyfo.app.common.utils.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface WebSocketCommonHandle {

	static Logger log = LoggerFactory.getLogger(WebSocketCommonHandle.class);
	
	/**
	 * 
	 * 校验sid是否可用，在这里写自己的逻辑
	 * 
	 * @param sid
	 * @return 返回发送消息时的唯一标识key
	 */
	default String verify(String sid) {
		
		log.info("校验"+sid);
		
		return sid;
		
	};
	
	/**
	 * 
	 * 接收到消息
	 * 
	 * 在这里写自己的逻辑
	 * 
	 * @param sid 发送消息时的唯一标识key
	 * @param message 收到的消息内容
	 * 
	 * @return 
	 */
	default void receiveMessage(String sid,String message) {
		
		log.info("接收到"+sid+"的消息："+message);
		
		
	};
	
}

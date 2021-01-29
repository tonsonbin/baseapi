package com.tb.app.model.websocket.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.configurer.websocket.WebSocketCommonMessage;
import com.tb.app.configurer.websocket.WebSocketCommonUtils;
import com.tb.app.configurer.websocket.entity.WebSocketUserInfo;

import net.sf.json.JSONObject;

@RequestMapping("${apiPath}/websocket")
@RestController
public class WebsocketSendMessage {

	/**
     * 
     * 推送消息
     * 
     * @param channelId 分组
     * @param sid 用户
     * @return
     */
    @PostMapping("/unauth/send")
    public Result send(String channelId,String sid,@RequestBody JSONObject message) {
    	
		WebSocketCommonUtils.sendMessage(WebSocketCommonMessage.init(sid,channelId).addUserMessage(message.toString()));
			
        return ResultGenerator.genSuccessResult();
    }
    
    /**
     * 
     * 推送消息
     * 
     * @param channelId 分组
     * @param sid 用户
     * @return
     */
    @PostMapping("/unauth/sendBody")
    public Result send(@RequestBody JSONObject wsInfo) {
    	
    	String channelId = wsInfo.getString("channelId");
    	String sid = wsInfo.getString("sid");
    	JSONObject message = wsInfo.getJSONObject("message");
    	
		WebSocketCommonUtils.sendMessage(WebSocketCommonMessage.init(sid,channelId).addUserMessage(message.toString()));
			
        return ResultGenerator.genSuccessResult();
    }
    
    /**
     * 
     * 推送消息
     * 
     * @param sid 消息发送的唯一标识
     * @param key 消息的key
     * @param message 消息内容
     * @return
     */
    @PostMapping("/unauth/sendSimple")
    public Result send(String channelId,String sid,String key,String message) {
    	
		WebSocketCommonUtils.sendMessage(WebSocketCommonMessage.init(sid,channelId).addUserMessage(message.toString()));
			
        return ResultGenerator.genSuccessResult();
    }
}

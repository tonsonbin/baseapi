package com.tyfo.app.configurer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tyfo.app.common.utils.websocket.WebSocketCommonHandle;
import com.tyfo.app.common.utils.websocket.WebSocketCommonUtils;

import groovy.util.logging.Slf4j;
import net.sf.json.JSONObject;

/**
 * Created by huiyunfei on 2019/5/31.
 */
 
@ServerEndpoint("/websocket/{channelId}/{sid}")
@Component
@Slf4j 
public class WebsocketServer extends WebSocketCommonUtils implements WebSocketCommonHandle{
	
	private static Logger log = LoggerFactory.getLogger(WebsocketServer.class);
	
    //session
    private Session session;
 
    //接收sid
    private String sid="";
 
    //渠道id
    private String channelId="";
 
 
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("channelId") String channelId, @PathParam("sid") String sid) {
    	
    	log.info("ONOPEN METHOD：channelId="+channelId+",sid="+sid);
    	if (StringUtils.isBlank(channelId)) {
    		throw new RuntimeException("分组不能为空！");
    	}
    	if (StringUtils.isBlank(sid)) {
    		throw new RuntimeException("用户不能为空！");
    	}
    	
        //调用校验方法
        String newSid = verify(sid);
    	if (StringUtils.isBlank(newSid)) {
			throw new RuntimeException("校验失败，不可连接！");
		}
    	
        this.session = session;
        this.sid = newSid;
        this.channelId = channelId;
        
        addWs(channelId, newSid, this);     //加入缓存中
        JSONObject messJsonObject = new JSONObject();
        messJsonObject.put("sysMessage", "连接成功");
        
        sendMessage(messJsonObject.toString(),channelId,newSid);
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("channelId") String channelId,@PathParam("sid") String sid) {
    	log.info("ONOPEN ONCLOSE：channelId="+channelId+",sid="+sid);
    	removeWs(channelId,sid);
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(@PathParam("channelId") String channelId,@PathParam("sid") String sid,String message, Session session) {
    	log.info("ONOPEN ONMESSAGE：channelId="+channelId+",sid="+sid+",message="+message);
    	receiveMessage(sid, message);
    }
    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error,@PathParam("channelId") String channelId,@PathParam("sid") String sid) {
    	log.error("ONOPEN ONERROR：channelId="+channelId+",sid="+sid+",error="+error.getLocalizedMessage());
        error.printStackTrace();
    }
    
	public Session getSession() {
		return session;
	}
	public String getSid() {
		return sid;
	}
	public String getChannelId() {
		return channelId;
	}
    
}
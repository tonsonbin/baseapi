package com.tyfo.app.configurer;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import groovy.util.logging.Slf4j;

/**
 * Created by huiyunfei on 2019/5/31.
 */
 
@ServerEndpoint("/websocket/{sid}")
@Component
@Slf4j
public class WebsocketServer {
	
	private static Logger log = LoggerFactory.getLogger(WebsocketServer.class);

	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String, WebsocketServer> webSocketServers = new ConcurrentHashMap<String, WebsocketServer>();
 
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
 
    //接收sid
    private String sid="";
 
 
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
    	
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
    	
        this.session = session;
        addOnlineCount();           //在线数加1
        this.sid=sid;
        
        webSocketServers.put(sid,this);     //加入缓存中
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("sid") String sid) {
    	webSocketServers.remove(sid);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+sid+"的信息:"+message);
    }
    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    
    /**
     * 实现服务器主动推送
     */
    public static void sendMessage(String message,String sid) throws IOException {
    	if (!webSocketServers.containsKey(sid)) {
            log.error("无该sid对应的连接信息！");
			return;
		}
    	WebsocketServer websocketServer = webSocketServers.get(sid);
    	websocketServer.session.getBasicRemote().sendText(message);
    }
    
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        WebsocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        WebsocketServer.onlineCount--;
    }
}
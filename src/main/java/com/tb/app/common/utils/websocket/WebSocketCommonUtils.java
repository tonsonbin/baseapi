package com.tb.app.common.utils.websocket;

import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tb.app.common.utils.lockTools.LockUtils;
import com.tb.app.configurer.WebsocketServer;

/**
 * websocket工具类
 * 
 * 增加分组（可以多渠道多组员，组员可以在不同组多端连接，但是组员不可以在同一个组连接多次）
 * 
 * 
 * @author Think
 *
 */
public class WebSocketCommonUtils {

	private static Logger log = LoggerFactory.getLogger(WebSocketCommonUtils.class);
    //组员对应的wsession对象
    private static Map<String, WebsocketServer> webSocketServers = Maps.newHashMap();
    //分组
    private static Map<String, List<String>> groups = Maps.newHashMap();
	
    /**
     * 推送消息
     * @param message 发送的信息
     * @param channelId 分组id
     * @param sid 组员id
     */
    public static void sendMessage(String message,String channelId,String sid){
    	
    	WebsocketServer websocketServer = option(2, channelId, sid, null);
    	if (websocketServer == null) {
    		log.error("发送信息，分组："+channelId+"，用户："+sid+",未找到对应的连接信息");
    		return;
		}
    	sendMessage(websocketServer,message);
    	
    }
    
    /**
     * 添加wsSession对象
     * @param channelId 消息对象的唯一标识
     * @param sid 消息对象的唯一标识
     * @param websocketServer wsSession对象
     */
    protected static void addWs(String channelId,String sid,WebsocketServer websocketServer) {

    	option(0, channelId, sid, websocketServer);
        log.info("有新窗口开始监听：分组："+channelId+"，用户："+sid+"，当前在线人数为" + webSocketServers.size());
    }
    

    /**
     * 移除wsSession对象
     * @param channelId 消息对象的唯一标识
     * @param sid 消息对象的唯一标识
     */
    protected static void removeWs(String channelId,String sid) {

    	option(1, channelId, sid, null);
        log.info("有窗口关闭:分组："+channelId+"，用户："+sid+",当前在线人数为" + webSocketServers.size());
    }
    
    /**
     * 操作类型
     * @param optionType 0-添加，1-移除，2-获取
     * @param channelId 消息对象的唯一标识
     * @param sid 消息对象的唯一标识
     * @param websocketServer wsSession对象
     * @return
     */
    private static WebsocketServer option(int optionType,String channelId,String sid,WebsocketServer websocketServer) {
    	
    	//组员id
    	String csid = channelId+"|-|"+sid;
    	
    	LockUtils.lock(csid);
    	try {
			
    	if (groups == null) {
			groups = Maps.newHashMap();
		}
    	if (webSocketServers == null) {
			webSocketServers = Maps.newHashMap();
		}
    	
    	//该分组的组员列表
		List<String> sids = groups.get(channelId);
		if (sids == null) {
			sids = Lists.newArrayList();
		}
    	
    	if (optionType == 0) {
			
    		//添加
    		if (!groups.containsKey(channelId)) {
    			
				//没有该渠道分组
    			//加入进分组，维护组员
    			sids.add(sid);
    			//维护分组信息
    			groups.put(channelId,sids );
    			
			}else {
				
				//已经存在分组
    			//判断是否存在该组员
				if (!sids.contains(sid)) {
					
					//不存在组员
					//添加组员
					sids.add(sid);
					
				}
				
			}
			//维护组员信息
			webSocketServers.put(csid, websocketServer);
    		
		}else if(optionType == 1){
			
			//移除
			if (sids.contains(sid)) {
				//移除组员列表
				sids.remove(sid);
				//移除组员信息
				webSocketServers.remove(csid);
			}
			
		}else if(optionType == 2){
			
			//获取
			if (sids.contains(sid)) {
				
				return webSocketServers.get(csid);
				
			}
		}
    	

		} catch (Exception e) {
			// TODO: handle exception
		}finally {

	    	LockUtils.unLock(csid);
			
		}
    	
    	return null;
    }
    
    /**
     * 
     * 规定统一的消息发送
     * 
     * @param session wsSession
     * @param message 要发送的消息
     */
    private static void sendMessage(WebsocketServer websocketServer,String message) {
    	
    	Session session = websocketServer.getSession();
    	String sid = websocketServer.getSid();
    	String channelId = websocketServer.getChannelId();
    	
    	try {
    		
			session.getBasicRemote().sendText(message);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			//如果报错则表示该session已经断开，从session集中移除
	        log.error("发送消息失败:分组："+channelId+"，用户："+sid+"，当前在线人数为" + webSocketServers.size());
			removeWs(channelId,sid);
		}
    	
    }
}

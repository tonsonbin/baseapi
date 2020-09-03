
/**
 * websocket js辅助代码
 * 
 * 配置了断线重连机制
 * 
//1、页面调用形式，登录系统的第一个页面或是主框架里进行初始化

var protocol = "${pageContext.request.protocol}";
var preWSign = "ws";
var preHttpSign = "http";
if(protocol.toLowerCase().indexOf("https") > -1){//https请求
	preWSign = "wss";
	preHttpSign = "https";
}

TBWebSocket.init({
	
	"webSocketUrl":preWSign+"://${pageContext.request.serverName}:${pageContext.request.serverPort}${ctx}/webSocketServer"
	,"sockjsUrl":preHttpSign+"://${pageContext.request.serverName}:${pageContext.request.serverPort}${ctx}/sockjs/webSocketServer"
	,"onmessage":function(jsonData){
		
		
	}
});

//2、子页面，或是已经打开过连接的页面需要接收消息处理，直接调用addEvent添加通知
top.TBWebSocket.addEvent({//如果子页面和主页面是引的同一个js，就可以直接用TBWebSocket.addEvent
		
		"id":"wsOnlineUserManager"//此次通知id
		,"onmessage":function(jsonData){//消息通知事件
			
			
		}
	});
 */
TBWebSocket = {
		
		websocket:""//构建的websocket连接对象
		,limitConnectNumLog:100//最大重连次数记录
		,limitConnectNum:""//最大重连次数
		,params:""//记录初始化参数
			
		,eventsIds:{}//记录所有的事件id，避免出现通知多次的情况
		,events:{
			
			onclose:[]
			,onopen:[]
			,onmessage:[]
			,onerror:[]
			
			
		}//所有事件
		/**
		 * 
		 * 初始方法，及调用入口
		 * 
		 * 第一次必选传params，后面就可以不用传了
		 * 
		 * params{
		 * 
		 * 	webSocketUrl://ws连接地址 notEmpty
		 *  ,sockjsUrl://http连接地址 notEmpty
		 *  ,onmessage://接收到消息事件 notEmpty
		 *  ,onopen://连接打开事件
		 *  ,onerror://连接失败事件
		 *  ,onclose://连接关闭事件
		 *  ,limitConnectNum://最大重连次数，默认100
		 * 
		 * }
		 * 
		 */
		,init:function(params){
			
			//没有构建，则进行第一次构建
			TBWebSocket.build(params);
			
			return TBWebSocket;
		},
		/**
		 * 构建连接
		 * 
		 * params{
		 * 
		 * 	webSocketUrl://ws连接地址 notEmpty
		 *  ,sockjsUrl://http连接地址 notEmpty
		 *  ,onmessage://接收到消息事件 notEmpty
		 *  ,onopen://连接打开事件
		 *  ,onerror://连接失败事件
		 *  ,onclose://连接关闭事件
		 *  ,limitConnectNum://最大重连次数，默认100
		 * 
		 * }
		 */
		build:function(params){
			
			if(TBWebSocket.params == ""){//如果记录的params为空，则判断是否传入了
				
				if(!params){//如果没有传入就初始化

					params = {};
					
				}else TBWebSocket.params = params;//记录为空，且出入不为空则将记录置为出入参数
				
			}else params = TBWebSocket.params;//如果记录不为空，则使用记录的参数
			
			var webSocketUrl = params.webSocketUrl;
			var sockjsUrl = params.sockjsUrl;
			
			if(!webSocketUrl){
				alert("webSocketUrl undefied");
				return;
			}
			
			if(!sockjsUrl){
				alert("sockjsUrl undefied");
				return;
			}
			
			var onmessage = params.onmessage;//接收到消息事件
			var onopen = params.onopen;//连接打开事件
			var onerror = params.onerror;//连接失败事件
			var onclose = params.onclose;//连接关闭事件
			var limitConnectNumLog = params.limitConnectNum;//最大重连次数
			if(!limitConnectNumLog){
				limitConnectNumLog = TBWebSocket.limitConnectNumLog;
			};
			
			if(TBWebSocket.limitConnectNum == ""){
				TBWebSocket.limitConnectNum = limitConnectNumLog;
			}
			
			TBWebSocket.events.onclose.push(onclose);
			TBWebSocket.events.onopen.push(onopen);
			TBWebSocket.events.onerror.push(onerror);
			TBWebSocket.events.onmessage.push(onmessage);
			
			var websocket;
			if ('WebSocket' in window) {
				websocket=new WebSocket(webSocketUrl);
			} else if ('MozWebSocket' in window) {
				websocket = new MozWebSocket(webSocketUrl);
			} else {
				websocket = new SockJS(sockjsUrl);
			}
			websocket.onmessage = function(evnt) {
				TBWebSocket.limitConnectNum = TBWebSocket.limitConnectNumLog;//链接成功，重置可链接次数
				var jsonData=eval("("+evnt.data+")");
				onmessage(jsonData);
			};
			websocket.onopen=function(evnt){
				if(onopen){
					onopen(evnt);
				}
			};
			websocket.onerror=function(evnt){
				if(onerror){
					onerror(evnt);
				}
			};
			websocket.onclose=function(evnt){
				if(onclose){
					onclose(evnt);
				}
				TBWebSocket.limitConnectNum = TBWebSocket.limitConnectNum - 1;
				if(TBWebSocket.limitConnectNum >= 0){

					console.log("正在重连，剩余重连次数："+TBWebSocket.limitConnectNum);
					setTimeout(TBWebSocket.init,5000);
					
				}
			};
			
			TBWebSocket.websocket = websocket;
		}
		/**
		 * 添加事件
		 * params{
		 *  ,id:""//此次添加的事件id，这是避免同一个页面出现多次通知，不要用随机数！
		 *  ,onmessage://接收到消息事件 notEmpty
		 *  ,onopen://连接打开事件
		 *  ,onerror://连接失败事件
		 *  ,onclose://连接关闭事件
		 * 
		 * }
		 */
		,addEvent : function(params){
			
			if(!params){
				params = {}
			}
			
			if(!params.id){
				
				alert("事件id不能为空！");
				
			}
			
			var eventsIds = TBWebSocket.eventsIds;
			var events = TBWebSocket.events;
			if(eventsIds[params.id]){//存在该id
				
				return;
				
			}
			
			eventsIds[params.id] = params;
			
			for(var key in params){
				
				if(events.hasOwnProperty(key)){
					var fun = params[key];
					
					events[key].push(fun);
				}
				
			}
			
			websocket = TBWebSocket.init().websocket;
			
			websocket.onmessage = function(evnt) {
				TBWebSocket.limitConnectNum = TBWebSocket.limitConnectNumLog;//链接成功，重置可链接次数
				var jsonData=eval("("+evnt.data+")");
				
				var eventsH = events.onmessage;
				for(var index in eventsH){
					
					var item = eventsH[index];
					item(jsonData);
				}
				
			};
			websocket.onopen=function(evnt){
				
				var eventsH = events.onopen;
				for(var index in eventsH){
					
					var item = eventsH[index];
					item(event);
				}
			};
			websocket.onerror=function(evnt){
				
				var eventsH = events.onerror;
				for(var index in eventsH){
					
					var item = eventsH[index];
					item(event);
				}
			};
			websocket.onclose=function(evnt){
				
				var eventsH = events.onclose;
				for(var index in eventsH){
					
					var item = eventsH[index];
					item(event);
				}
			};
			
		}
}

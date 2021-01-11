
/**
 * websocket js辅助代码
 * 
 * 配置了断线重连机制
 * 
 * 页面调用形式
 *  var webSocketCal=new tbwebsocket();
 *  //设定断线重连连接次数
 *  webSocketCal.setLimitConnectNum(100);
 *  //https的连接
	webSocketCal.webSocketUrl="wss://xxx/xxx/a/webSocketServer";
	webSocketCal.sockjsUrl="https://xxx/xxx/a/sockjs/webSocketServer";
 *  //http的连接
	//webSocketCal.webSocketUrl="ws://xxx/xxx/a/webSocketServer";
	//webSocketCal.sockjsUrl="http://xxx/xxx/a/sockjs/webSocketServer";
	webSocketCal.setOnerrorEvnt(function(){
		console.log("error");
	});
	webSocketCal.setOncloseEvnt(function(){

		console.log("close");
	});
	webSocketCal.setMessageEvnt(function(jsonData){
	
		
	
	});
	webSocketCal.init();
 */
tbwebsocket=function(){
	this.webSocket=null;
	this.webSocketUrl=null;
	this.sockjsUrl=null;
	//私有function
	var onMessage;
	var onOpen;
	var onError;
	var onClose;
	var fiConnectNum;
	var limitConnectNum;//重新连接指定次数
	this.init=function(){
		if(this.webSocketUrl == null || this.webSocketUrl == undefined || this.webSocketUrl == ''){
			alert("webSocketUrl undefied");
		}else if(this.sockjsUrl == null || this.sockjsUrl == undefined || this.sockjsUrl == ''){
			alert("sockjsUrl undefied");
		}
		if ('WebSocket' in window) {
			this.webSocket=new WebSocket(this.webSocketUrl);
		} else if ('MozWebSocket' in window) {
			this.webSocket = new MozWebSocket(this.webSocketUrl);
		} else {
			this.webSocket = new SockJS(this.sockjsUrl);
		}
		this.webSocket.onmessage = function(evnt) {
			limitConnectNum = fiConnectNum;//链接成功，重置可链接次数
			var jsonData=eval("("+evnt.data+")");
			onMessage(jsonData);
		};
		this.webSocket.onopen=function(evnt){
			if(onOpen){
				onOpen(evnt);
			}
		};
		this.webSocket.onerror=function(evnt){
			if(onError){
				onError(evnt);
			}
		};
		this.webSocket.onclose=function(evnt){
			if(onClose){
				onClose(evnt);
			}
			limitConnectNum = limitConnectNum - 1;
			if(limitConnectNum >= 0){

				this.layConntect(limitConnectNum);
				
			}
		};
	};
	this.layConntect=function(num){
		console.log("正在重连，剩余重连次数："+num);
		setTimeout(this.init,5000);
	}
	this.setMessageEvnt=function(messageEvnt){
		onMessage=messageEvnt;
	};
	this.setOpenEvnt=function(openEvnt){
		onOpen=openEvnt;
	};
	this.setOnerrorEvnt=function(errorEvnt){
		onError=errorEvnt;
	};
	this.setOncloseEvnt=function(closeEvnt){
		onClose=closeEvnt;
	};
	this.setLimitConnectNum=function(limitNum){
		fiConnectNum = limitNum;
		limitConnectNum = fiConnectNum;
	}
};
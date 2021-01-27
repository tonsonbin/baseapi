<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title></title>

		<style>
			ul {
				font-size: 14px;
				color: #8f8f94;
			}
			.mui-btn {
				padding: 10px;
			}
		</style>
	</head>

	<body>
		<sys:head title="demo" back="1"/>
		
		<div class="mui-content mui-scroll-wrapper mui-scroll-wrapper-scroll mui-bottom-menu">
		
			<div id="123" style="width:100%;height:100%;" style="background-color:rgb(100,100,100)">
				<!-- 顶部 -->
				<div class="top_box" style="width:100%;height:15%;background-color:rgb(240,240,240);border-bottom:1px solid gray">
				</div>
				<!-- 中间 -->
				<div class="middle_box" style="width:100%;height:80%;background-color:rgb(240,240,240)">
					<!-- 中间左部 -->
					<div id="left_box" class="left_box" style="width:20%;padding:5px;height:100%;float:left;background-color:rgb(240,240,240)">
						
					</div>
					<!-- 中间中部 -->
					<div class="center_box" style="width:60%;height:100%;float:left;background-color:rgb(180,180,180)">
						<!-- 中部显示信息 -->
						<div id="cb_disMessage" class="cb_disMessage" style="width:100%;height:90%;float:left;padding:2%;overflow-y:scroll;overflow-x:hidden;border-left:1px solid gray;background-color:rgb(240,240,240)">
							
						</div>
						<!-- 中部发送信息 -->
						<div class="cb_sendMessage" style="width:100%;height:10%;float:left;background-color:rgb(220,220,220)">
							<!-- 信息录入匡 -->
							<div style="width:80%;float:left;">
								<textarea id=sendMessage name="xxx" style="height:100%;width:100%;resize:none;outline:none"></textarea>
							</div>
							<!-- 发送按钮 -->
							<div style="width:20%;height:100%;float:left;">
								<button onclick="send()" style="width:100%;height:100%;">send message</button>
							</div>
						</div>
					</div>
					<div class="right_box" style="width:20%;height:100%;float:left;border-left:1px solid gray;background-color:rgb(240,240,240)">
					</div>
				</div>
				<!-- 底部 -->
				<div class="bottom_box" style="width:100%;height:5%;text-align:center;">
					copyright 2016-10-31 @tb chat 编号12345334567654
				</div>
			</div>
		  
		</div>
<script type="text/javascript">

var protocol = "${pageContext.request.protocol}";
var preWSign = "ws";
var preHttpSign = "http";
if(protocol.toLowerCase().indexOf("https") > -1){//https请求
	preWSign = "wss";
	preHttpSign = "https";
}

var port = "${pageContext.request.serverPort}";
var ports = ":"+port;
if(preHttpSign == "https" && port == "443"){
	//https，并且端口是443，则不拼接端口
	ports = "";
}

var sid = new Date().getTime();

var webSocketUrl = "wss://whc.tyfo.com/tyfowebsocket/websocket/qywxapp/18780091029";//"wss://192.168.20.33:30000/tyfowebsocket/websocket/qywxapp/phone";//preWSign+"://${pageContext.request.serverName}"+ports+"${ct}/websocket/test/"+sid;//
var sockjsUrl = "https://whc.tyfo.com/tyfowebsocket/websocket/qywxapp/18780091029";//"https://192.168.20.33:30000/tyfowebsocket/websocket/qywxapp/phone";//preHttpSign+"://${pageContext.request.serverName}"+ports+"${ct}/websocket/test/"+sid;//

TBWebSocket.init({
	
	"webSocketUrl":webSocketUrl
	,"sockjsUrl":sockjsUrl
	,"limitConnectNum":100
	,"onmessage":function(jsonData){
		
		console.log(jsonData);
		//系统提示消息
		var sysMessage = jsonData.sysMessage;
		//用户上线消息
		var sysMessage_userOnline = jsonData["sysMessage-userOnline"];
		//用户发送的消息
		var userMessage = jsonData.userMessage;
		
		if(sysMessage){
			sysMess(sysMessage);
		}
		
		if(sysMessage_userOnline){
			sysMess(sysMessage_userOnline);
		}
		
		if(userMessage){
			if(sid==userMessage.userId){//用户自己的消息
				myselfMess(userMessage.userId,userMessage.message);
			}else
				chatMess(userMessage.userId,userMessage.message);
		}
		/* if(messageType=="3"){//系统消息
			sysMess(message.message);
		}else if(messageType=="2"){//用户信息列表
			document.getElementById("left_box").innerHTML="";
			var jsonUserInfos=eval('('+message.message+')');
			for(var key in jsonUserInfos){
				user=jsonUserInfos[key];
				disUserInfo(user.userName);
			}
		}else if(messageType=="1"){//普通聊天信息
			if(message.sendUserName==userInfo.userName){//用户自己的消息
				myselfMess(message.sendUserName,message.message);
			}else
				chatMess(message.sendUserName,message.message);
		}else if(messageType=="5"&&userInfo==null){//携带的个人信息
			userInfo=message.message;
			userInfo=eval('('+userInfo+')');
		}; */
	}
});

function send(){
	var message =document.getElementById("sendMessage").value;
    if(message !=null && message !=""){
        document.getElementById("sendMessage").value="";
        TBWebSocket.websocket.send(message);
    }
};	
function resizeTextArea(){
	/* $("textarea").each(function(){
		$(this).css("height",$t.windowHeight*0.08);
	}); */
}
function sysMess(mess){
	var div = document.createElement("div");
	div.setAttribute("style","width: 100%;color:gray;text-align:center;display:block;word-break: break-all;word-wrap: break-word;height:auto;padding:5px;");
	div.innerHTML=mess;
	document.getElementById("cb_disMessage").appendChild(div);
}
function myselfMess(sendUserName,mess){
	var div = document.createElement("div");
	div.setAttribute("style","width:100%;float:left;");
	div.innerHTML='<div style="width:50px;float:right;">'+
	'<img src="${ctxStatic }/base/images/login-bg.jpg" style="width:100%;height:50px;border-radius:5px 5px 5px 5px;"/>'+
	'</div>'+
	'<div style="width:auto;height:auto;float:right;max-width:80%;">'+
		'<div style="width:auto;height:30px;padding:15px;text-align:right;">'+
			sendUserName+
		'</div>'+
		'<div style="width: auto;display:block;word-break: break-all;word-wrap: break-word;height:auto;margin:25px;background-color:#FFE1FA;padding:5px;border-radius:5px 5px 5px 5px;border:1px solid #CE9E9E;">'+
			mess+
		'</div>'+
	'</div>';
	document.getElementById("cb_disMessage").appendChild(div);
}
function chatMess(sendUserName,mess){
	var div = document.createElement("div");
	div.setAttribute("style","width:100%;float:left;");
	div.innerHTML='<div style="width:50px;float:left;">'+
	'<img src="${ctxStatic }/base/images/login-bg.jpg" style="width:100%;height:50px;border-radius:5px 5px 5px 5px;"/>'+
	'</div>'+
	'<div style="width:auto;height:auto;float:left;max-width:80%;">'+
		'<div style="width:auto;height:30px;padding:15px;">'+
			sendUserName+
		'</div>'+
		'<div style="width: auto;display:block;word-break: break-all;word-wrap: break-word;height:auto;margin:25px;background-color:#FFE1FA;padding:5px;border-radius:5px 5px 5px 5px;border:1px solid #CE9E9E;">'+
			mess+
		'</div>'+
	'</div>';
	document.getElementById("cb_disMessage").appendChild(div);
}
function disUserInfo(userName){
	var div = document.createElement("div");
	div.setAttribute("style","width:300%;float:left;margin-top:5px;");
	div.innerHTML='<div style="float:left">'+
	'<img src="${ctxStatic }/base/images/login-bg.jpg" style="width:25px;height:25px;"/>'+
	'</div>'+
	'<div style="float:left">'+
		userName+
	'</div>';
	document.getElementById("left_box").appendChild(div);
	var br = document.createElement("br");
	document.getElementById("left_box").appendChild(br);
}
</script>
	</body>

</html>
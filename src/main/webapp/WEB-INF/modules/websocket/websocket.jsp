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
						输入 name:xxxx 就可以定义自己的名字
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

var webSocketUrl = preWSign+"://${pageContext.request.serverName}"+ports+"${ct}/websocket/test/"+sid;//
var sockjsUrl = preHttpSign+"://${pageContext.request.serverName}"+ports+"${ct}/websocket/test/"+sid;//

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
		//用户发送消息内容
		var userMessage = jsonData.userMessage;
		//接收的用户的信息
		var receiveUserInfo = jsonData.receiveUserInfo;
		//发送的用户的信息
		var sendUserInfo = jsonData.sendUserInfo;
		
		if(sysMessage){
			sysMess(sysMessage);
		}
		
		if(sysMessage_userOnline){
			sysMess(sysMessage_userOnline);
		}
		
		if(userMessage){
			/* if(receiveUserInfo && receiveUserInfo.userId){//用户自己的消息
				myselfMess(userMessage.name,userMessage);
			} */
			if(sendUserInfo && sendUserInfo.userId){
				
				chatMess(sendUserInfo.name,userMessage);
				
			}
				
		}
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
</script>
	</body>

</html>
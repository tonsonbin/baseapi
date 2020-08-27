<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
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
		<sys:head title="demo" back="1" menuRight="1"/>
		<script>
			(function(mui, doc) {

				headerReresh({
					
					//右侧菜单点击事件
					"rightClick":function(){
						
						//这里调用渲染弹窗示例
						menuSLDown.toggle({
							"id":"0"
						});
						
					}
					
				});
				
			}(mui, document));
			
		</script>
		
		
		<div class="mui-content mui-scroll-wrapper mui-scroll-wrapper-scroll mui-bottom-menu">
		
			
		  
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
//"ws://localhost:8080/reddragonapi/websocket/testtb";//
//"http://localhost:8080/reddragonapi/websocket/testtb";//
var webSocketUrl = "wss://testservices.tyfo.com:13860/reddragonapi_websocket/websocket/testtb";//preWSign+"://${pageContext.request.serverName}"+ports+"${ct}/websocket/server";
var sockjsUrl = "https://testservices.tyfo.com:13860/reddragonapi_websocket/websocket/testtb";//preHttpSign+"://${pageContext.request.serverName}"+ports+"${ct}/websocket/server";

TBWebSocket.init({
	
	"webSocketUrl":webSocketUrl
	,"sockjsUrl":sockjsUrl
	,"onmessage":function(jsonData){
		
		for(var messageIndex in jsonData){
			var message=jsonData[messageIndex];
			var messageType=message.messageType;
			if(messageType=="6"){//普通的弹窗提示
				//弹窗提醒
				lawh.dialog({"title":"系统提示","content":message.message});
			}else if(messageType=="5"){//携带的个人信息
				userInfo=message.message;
				console.log(userInfo);
			}else if(messageType=="3"){//系统消息
				console.log(message.message);
			}else if(messageType=="2"){//用户信息列表
				var jsonUserInfos=eval('('+message.message+')');
				var data = [];
				for(var key in jsonUserInfos){
					user=jsonUserInfos[key];
					data.push(user);
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
			};  */
			}
		}
	}
});
</script>
	</body>

</html>
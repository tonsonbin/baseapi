<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>错误啦</title>
<style>
body{
	background:white;
}
</style>
</head>
<body>

<div class="mui-content" style="background:white;padding-top:50%">
    <div class="mui-row">
        <div class="mui-col-sm-6">
            <span class="mui-icon"><img src="${ctxStatic }/base/images/error.jpg" style="width:100%"/></span>
						<div class="mui-media-body" style="text-align:center;">
						<p style="word-break:break-all">
							${code }<br/>
							${message }<br/>
							${isi }
						</p></div>
        </div>
    </div>
</div>
</body>
</html>
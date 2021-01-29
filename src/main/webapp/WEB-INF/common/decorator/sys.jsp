<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/common/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property="title" /></title><!--  - Powered By coolsn -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	
	<!-- mui -->
	<script src="${ctxStatic}/mui-3.3.0/js/mui.min.js"></script>
	<link rel="stylesheet" href="${ctxStatic}/mui-3.3.0/css/mui.min.css">
	<script src="${ctxStatic}/mui-3.3.0/js/muiHelper.js?v=1.0.3"></script>
	
	<sitemesh:write property="head" />
</head>
<body class="layui-layout-body">
	<sitemesh:write property="body" />
</body>
</html>
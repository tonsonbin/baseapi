<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property="title" /></title><!--  - Powered By coolsn -->
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	
	<script>

		var ctview = "${ctview}";
		var ctapi = "${ctapi}";
		var ctxStatic = "${ctxStatic}";
	
	</script>
	<!-- jquery -->
	<script src="${ctxStatic}/jquery/jquery-3.5.1.min.js?v=1.0.0"></script>
	<!-- 数字计算工具 -->
	<script src="${ctxStatic}/math/math.min.js?v=1.0.0"></script>
	
	<!-- 浏览器缓存工具 -->
	<script src="${ctxStatic}/myjs/localStorageHelper.js?v=1.0.2"></script>
	
	<!-- 数据校验辅助工具 -->
	<script src="${ctxStatic}/myjs/tb_judge1.1.js?v=1.0.2"></script>
	
	<!-- 时间格式化工具 -->
	<script src="${ctxStatic}/myjs/dateFormat.js?v=1.0.2"></script>
	
	<!-- 日志辅助工具 -->
	<script src="${ctxStatic}/myjs/logHelper.js?v=1.0.2"></script>
	
	<!-- 倒计时工具 -->
	<script src="${ctxStatic}/myjs/transformTime1.1.js?v=1.0.3"></script>
	
	<!-- swiper轮播 -->
	<script src="${ctxStatic}/swiper/swiper6.0.4/swiper-bundle.min.js?v=1.0.0"></script>
	<link rel="stylesheet" href="${ctxStatic}/swiper/swiper6.0.4/swiper-bundle.min.css?v=1.0.0">
	
	<!-- template -->
	<script src="${ctxStatic}/template/template.js?v=1.0.0"></script>
	
	<!-- ajax -->
	<script src="${ctxStatic}/myjs/ajaxHelper.js?v=1.1.1"></script>
	
	<!-- 登录工具 -->
	<link rel="stylesheet" href="${ctxStatic}/myjs/login/css/loginHelper.css?v=1.0.13">
	<script src="${ctxStatic}/myjs/loginHelper.js?v=1.0.2"></script>
	
	<style>
	
	.mui-bottom-menu{
	
		padding-bottom:44px;
	
	}
	
	.mui-scroll-wrapper-scroll{
	
		overflow:scroll !important;
	
	}
	
	</style>
	
	<sitemesh:write property="head" />
</head>
<body class="layui-layout-body">
	<sitemesh:write property="body" />
</body>
</html>
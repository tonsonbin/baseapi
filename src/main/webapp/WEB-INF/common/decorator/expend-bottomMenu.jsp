<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/common/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property="title" /></title>
	<sitemesh:write property="head" />
</head>
<body class="layui-layout-body">
	<sitemesh:write property="body" />
	<!-- 菜单 -->
	<nav id="bottom-menu" class="mui-bar mui-bar-tab">
		
	</nav>
	
<script id="tpl-bm" type="text/html">

	{{each list as item index}}
		<a class="mui-tab-item" href="{{item.href}}">
			<span class="mui-icon {{item.icon}}">
			{{if item.badge != undefined && item.badge != ''}}
				<span class="mui-badge">{{item.badge}}</span>
			{{/if}}
			</span>
			<span class="mui-tab-label">{{item.name}}</span>
		</a>
	{{/each}}

</script>
	
	<script type="text/javascript" charset="utf-8">

			//创建菜单
			var menuInfo = {list:[
				{
				
					"name":"首页"
					//,"badge":"9"
					,"icon":"mui-icon-home"
					,"href":"${ctview}/sys/index"
				}
				,{
					
					"name":"我的"
					//,"badge":"9"
					,"icon":"mui-icon-home"
					,"href":"${ctview}/sys/my"
				
				},{
					
					"name":"原始demo"
					//,"badge":"9"
					,"icon":"mui-icon-home"
					,"href":"${ctxStatic}/mui-pages/hello-mui/index.html"
				
				},{
					
					"name":"tag-demo"
					//,"badge":"9"
					,"icon":"mui-icon-home"
					,"href":"${ctview}/demo/index"
				
				}
			]}
			
			$("#bottom-menu").html(template("tpl-bm",menuInfo))

			 //当前激活选项
			var locationPathName = location.pathname;
			var sName = locationPathName.replace(/\//g,"\\/");

			$("a[href="+sName+"]").addClass("mui-active");
			//选项卡点击事件
			$(".mui-bar-tab").find("a").click(function(){
				
				var targetTab = $(this).attr('href');
				if(locationPathName == targetTab){//点击的当前页面
					return;
				}
				location.href = targetTab;
				
			});
		</script>
</body>
</html>
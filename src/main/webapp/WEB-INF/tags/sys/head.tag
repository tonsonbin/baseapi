<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/include/taglib.jsp"%>
<%@ attribute name="title" type="java.lang.String" required="false" description="标题"%>
<%@ attribute name="back" type="java.lang.String" required="false" description="是否显示返回（1 显示）"%>
<%@ attribute name="menuRight" type="java.lang.String" required="false" description="是否显示右侧菜单按钮（1 显示）"%>
<%@ attribute name="transparent" type="java.lang.String" required="false" description="是否透明（1 透明）"%>

<header id="header" class="mui-bar mui-bar-nav ${transparent eq 1?'mui-bar-transparent':'' }">
	<c:if test="${back eq 1 }"><a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a></c:if>
	<h1 class="mui-title">${title }</h1>
	<c:if test="${menuRight eq 1 }"><a id="2323fsfw-head-icon-menu" class="mui-action-menu mui-icon mui-icon-bars mui-pull-right"></a></c:if>
</header>

<style>

.mui-bar{

	z-index:999;

}

</style>

<script id="tpl" type="text/html">

</script>

<script type="text/javascript">

//构建slider
var buildHeader = function(params){
	
	var log = new LogHelper({"show":true});
	log.init({
		"className":"buildHeader"
	});
	log.append("参数",params);
	
	if(!params){
		
		params = {};
		
	}
	
	var rightClick = params.rightClick;
	
	if(rightClick){
		
		$("#2323fsfw-head-icon-menu").click(function(){

			rightClick();
			
		});
	}
	

	log.append("方法调用完成");
	log.print();
}
//刷新slider
/**
 * params：
 {
		
		"rightClick":function(){};//右边点击事件
		
	}
 */
var headerReresh = function(params){

	 buildHeader(params);
	
}

</script>
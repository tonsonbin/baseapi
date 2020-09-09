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
		
		<!-- *************************************************************下拉导航菜单********************************************* -->
			<sys:menu-sl-down id="0"/>
			<script>
			(function(mui, doc) {
				
				var menu_sl_down_data = [{name:"退出登录","event":"logout"}];
				
				menuSLDown.reresh({
					
					"id":"0"
					,"data":menu_sl_down_data
					,"itemNameKey":"name"//item名称的key
					,"click":function(itemData){//当前元素对应的列表的值
						
						console.log(itemData);
						
					}
					
				});
				
			}(mui, document));
			
		</script>
		
		<div class="mui-content mui-scroll-wrapper mui-scroll-wrapper-scroll mui-bottom-menu">
		
		<!-- *******************************************轮播图***********************************************-->
			<sys:sliderList id="0" />
			<sys:sliderList id="1" />
			<script>
			(function(mui, doc) {

				//加载轮播数据
				AjaxHelper.ajax({
					
					url:"${ctapi}/banner/unauth/list"
					,data:{}
					,success:function(data){
						sliderReresh({
							
							"id":"0"
							,"data":data
							,"picUrlKey":"url"//图片地址的key
							
						});    
					}
					
				});
				
			}(mui, document));
			
		</script>
		
		
		<!-- *************************************************************图文表格****************************************** -->
		<div class="mui-slider">
		        <div class="mui-slider-group mui-slider-loop">
		        	<!-- 额外增加的一个节点(循环轮播：第一个节点是最后一个图文表格) -->
		            <div class="mui-slider-item mui-slider-item-duplicate">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/cbd.jpg">
		                            <div class="mui-media-body">Color of SIP CBD</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/yuantiao.jpg">
		                            <div class="mui-media-body">静静看这世界</div></a></li>
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/shuijiao.jpg">
		                            <div class="mui-media-body">幸福就是可以一起睡觉</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/muwu.jpg">
		                            <div class="mui-media-body">想要一间这样的木屋，静静的喝咖啡</div></a></li>
		                </ul>
		            </div>
		            <div class="mui-slider-item">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/cbd.jpg">
		                            <div class="mui-media-body">Color of SIP CBD</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/yuantiao.jpg">
		                            <div class="mui-media-body">静静看这世界</div></a></li>
		                </ul>
		            </div>
		            <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一个图文表格) -->
		            <div class="mui-slider-item mui-slider-item-duplicate">
		                <ul class="mui-table-view mui-grid-view">
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/shuijiao.jpg">
		                            <div class="mui-media-body">幸福就是可以一起睡觉</div></a></li>
		                    <li class="mui-table-view-cell mui-media mui-col-xs-6"><a href="#"><img class="mui-media-object" src="../images/muwu.jpg">
		                            <div class="mui-media-body">想要一间这样的木屋，静静的喝咖啡</div></a></li>
		                </ul>
		            </div>
		        </div>
		        <div class="mui-slider-indicator" style="position: static;background-color: #fff;">
		            <span class="mui-action mui-action-previous mui-icon mui-icon-back"></span>
		            <div class="mui-number">
		                <span>1</span> / 2
		            </div>
		            <span class="mui-action mui-action-next mui-icon mui-icon-forward"></span>
		        </div>
		    </div>
		  
		<!-- *************************************************************九宫格表格****************************************** -->
		<sys:grid-pagination id="0" cssStyle="background:white"/>
		<script>
			(function(mui, doc) {

				gridPaginationReresh({
					
					"id":"0",
					"list":[{"name":"测试websocket","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg","url":"${ctview}/sys/websocket","event":"websocket"}
					,{"name":"测试登录工具","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg","event":"login"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"},
						{"name":"测试1","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}
					,{"name":"测试2","pic":"${ctxStatic }/mui-pages/hello-mui/images/shuijiao.jpg"}],
					"itemNameKey":"name",
					"itemPicUrlKey":"pic",
					"rows":2,
					"cols":2,
					"click":function(data){
						
						if(data.event == "websocket"){

							window.location.href = data.url;
							
						}else if(data.event == "login"){
							
							LoginHelper.showLogin();
							
						}
						
					}
					
				});
				
			}(mui, document));
			
		</script>
		  
	</div>
	</body>

</html>
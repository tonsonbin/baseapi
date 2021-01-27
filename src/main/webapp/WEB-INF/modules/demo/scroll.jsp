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
			<sys:menu-sl-down id="0" fontStyle="color:#dedede" cssStyle="color:white"/>
			<script>
			(function(mui, doc) {
				
				var menu_sl_down_data = [{name:"退出登录","event":"logout"},{name:"返回首页","event":"logout"}];
				
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
		<!-- *******************************************************下拉-上拉、轮播图***************************************************** -->
		<!--下拉刷新容器-->
		<div id="refreshContainer" class="mui-content mui-scroll-wrapper">
		  <div class="mui-scroll">
		    
		    <!-- *******************************************轮播图***********************************************-->
			<sys:sliderList id="0" />
			<sys:sliderList id="1" />
			<sys:sliderList id="2" />
		  </div>
		</div>
			<script>
			(function(mui, doc) {

				//上拉下拉数据处理
				var muiHelper = new MuiHelper(mui);
				muiHelper.pullRefresh({
					
					"container":"#refreshContainer"
					,"callbackDown":function(endPull){
						
						window.location.reload();
						
					},"callbackUp":function(endPull){
						
						dataInit(endPull);
						
					}
					
				});
				
				var dataInit = function(endPull){
					
					AjaxHelper.ajax({
						
						url:"${ctapi}/sys/banner/unauth/list"
						,data:{}
						,success:function(data){
							
							sliderReresh({
								
								"id":"2"
								,"data":data
								,"picUrlKey":"url"//图片地址的key
								
							});
						    
							if(endPull){

								endPull(true);
								
							}
						}
						
					});
					
				}
				
				//dataInit();
				
			}(mui, document));
			
		</script>
	</body>

</html>
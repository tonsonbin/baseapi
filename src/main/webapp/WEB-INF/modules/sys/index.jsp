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
		<sys:head title="首页" back="1"/>
		<!--下拉刷新容器-->
		<div id="refreshContainer" class="mui-content mui-scroll-wrapper">
		  <div class="mui-scroll">
		    <!--数据列表-->
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
						
						dataInit(endPull);
						
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
						    
							endPull(true);
						}
						
					});
					
				}
				
				//加载轮播数据
				AjaxHelper.ajax({
					
					url:"${ctapi}/sys/banner/unauth/list"
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
	</body>

</html>
var MuiHelper = function(mui){
	
	var thisObj = this;
	this.mui = mui;
	
	/**
	 * 
	 * params:
	 * {
	 * container:""//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
	 * ,callbackDown:function(endPull){//可选但与callbackUp必须有一个，下拉刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据重新渲染页面操作；
	 * 
	 * 		endPull(boolean);//下拉刷新没有所谓的停止，所以你没有想错，这里的这个没啥用
	 * 
	 * 	}
	 * ,callbackUp:function(endPull){//可选但与callbackDown必须有一个，上拉加载数据函数，根据具体业务来编写，比如通过ajax从服务器获取新数据重新渲染页面操作；
	 * 
	 * 		endPull(boolean);//在所有业务逻辑处理完之后调用，是否停止刷新，一般用于分页数据列表
	 * 
	 * 	}
	 * }
	 * 
	 */
	this.pullRefresh = function(params){
		
		if(!params){
			params = {};
		}
		
		var container = params.container;//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
		var callbackDown = params.callbackDown;
		var callbackUp = params.callbackUp;
		
		if(!container){
			mui.toast("container不能为空");
			return;
		}
		if(!callbackDown && !callbackUp){
			mui.toast("callbackDown和callbackUp不能都为空！");
			return;
		}
		
		var pullRefreshConfig = {
				
				container:container
				
		};
		if(params.callbackDown){
			
			pullRefreshConfig.down =  {
					
				      height:50,//可选,默认50.触发下拉刷新拖动距离,
				      auto: false,//可选,默认false.首次加载自动下拉刷新一次
				      contentdown : "下拉可以刷新",//可选，在下拉可刷新状态时，下拉刷新控件上显示的标题内容
				      contentover : "释放立即刷新",//可选，在释放可刷新状态时，下拉刷新控件上显示的标题内容
				      contentrefresh : "正在刷新...",//可选，正在刷新状态时，下拉刷新控件上显示的标题内容
				      callback :function(){//必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
				    	  
				    	  callbackDown(function(stop){
				    		  
				    		  var pr = mui(container).pullRefresh();
				    		  if(stop == undefined || stop == ""){
				    			  
				    			  stop = false;
				    			  
				    		  }

				    		  pr.endPulldownToRefresh();
				    		  
				    	  });
				    	  
				      } 
				    }
			
		}
		
		if(params.callbackUp){
			
			pullRefreshConfig.up =  {
					
				      height:50,//可选,默认50.触发下拉刷新拖动距离,
				      auto: false,//可选,默认false.首次加载自动下拉刷新一次
				      contentrefresh: "正在加载...",//可选，正在加载状态时，上拉加载控件上显示的标题内容
		              contentnomore: '没有更多数据了',//可选，请求完毕若没有更多数据时显示的提醒内容；
				      callback :function(){//必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
				    	  
				    	  callbackUp(function(params){
				    		  
				    		  var pr = mui(container).pullRefresh();
				    		  if(stop == undefined || stop == ""){
				    			  
				    			  stop = false;
				    			  
				    		  }
				    		  
				    		  pr.endPullupToRefresh(stop);
				    		  
				    	  });
				    	  
				      } 
				    }
			
		}
		
		mui.init({
		  pullRefresh : pullRefreshConfig
		});
		
	}
	
	
}
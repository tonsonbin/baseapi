/**
 * ajax 请求辅助封装
 * 
 * 建立在layui的基础上
 * author tb
 */
AjaxHelper = {
		
		codeKey:"code",//返回码的key
		codeSuccess:"200",//返回码成功的代码
		codeError:"false",//返回码失败的代码
		codeNeedLogin:"403",//需要登录
		msgKey:"message",//错误信息key
		
		urlLogin:ctview+"/a?login",//登录页面地址
		synInfo:{},//同步记录
		
		/**
		 * 取formData对象
		 * 
		 * params
		 * {
		 * 
		 * form:document form对象
		 * ,type:string 类型（默认空，正常表单，file：文件上传表单）
		 * 
		 * }
		 */
		formData:function(params){
			
			if(!params){
				params = {};
			}
			
			var form = params.form;
			var type = params.type;
			
			if(!form){
				
				form = document.createElement("form");
				
			}
			if(type == "file"){

	        	form.setAttribute("enctype","multipart/form-data");
	        	
			}
        	
        	var formData = new FormData(form);
			
        	return formData;
		},
		/**
		 * 普通的ajax请求
		 * params:{
		 * 	url:,//必须
		 * 	data:{},//非必须
		 * 	success:function(){//非必须
		 * 
		 * 	},
		 * 	error:function(){//错误，非必须
		 * 
		 * 	},
		 * 	login:function(){//登录逻辑，非必须（当校验到返回码是需要登录的码值时会调用该方法）
		 * 
		 * 	},
		 * 	done:function(){//一定会执行的方法
		 * 
		 * 	
		 * 	}
		 * 
		 * 	
		 * }
		 */
		ajax : function(params){

			//开启进度条
			mui('body').progressbar({
				progress: undefined
			}).show();
			$('body').find(".mui-progressbar").show();
			
    		var data = {};
    		var type = "post";
    		if(params.type){
    			data = params.type;
    		}
    		
    		if(params.data){
    			data = params.data;
    		}
    		$.ajax({
    			
    			url:params.url,
    			data:params.data,
    			type:type,
    			success:function(data){
    				AjaxHelper.successDel(params,data);
    			},
    			error:function(msg){

    				AjaxHelper.errorDel(params,msg);
    			}
    			
    		});
    		
    	},
    	/**
		 * 普通的ajax请求-限制同一个url只能同时存在一个请求，一般登录的时候调用这个方法
		 * params:{
		 * 	url:,//必须
		 * 	data:{},//非必须
		 * 	success:function(){//非必须
		 * 
		 * 	},
		 * 	error:function(){//错误，非必须
		 * 
		 * 	},
		 * 	login:function(){//登录逻辑，非必须
		 * 
		 * 	},
		 * 	done:function(){//一定会执行的方法
		 * 
		 * 	
		 * 	}
		 * 
		 * 	
		 * }
		 */
		ajaxSyn : function(params){

			if(!params){
				params = {};
			}
			var url = params.url;
			var synUrl = AjaxHelper.synInfo[url];
			if(synUrl){
				//请求中
				return;
			}else{
				
				//标识请求中
				AjaxHelper.synInfo[url] = true;
				
			}
			
			mui('body').progressbar({
				progress: undefined
			}).show();
			$('body').find(".mui-progressbar").show();
			
    		var data = {};
    		var type = "post";
    		if(params.type){
    			data = params.type;
    		}
    		
    		if(params.data){
    			data = params.data;
    		}
    		$.ajax({
    			
    			url:params.url,
    			data:params.data,
    			type:type,
    			success:function(data){
    				
    				//标识请求完成
    				AjaxHelper.synInfo[url] = false;
    				
    				AjaxHelper.successDel(params,data);
    			},
    			error:function(msg){

    				//标识请求完成
    				AjaxHelper.synInfo[url] = false;

    				AjaxHelper.errorDel(params,msg);
    			}
    			
    		});
    		
    	},
    	/**
    	 * formdata 参数提交
    	 * params:{
		 * 	url:,//必须
		 * 	data:formData,//非必须
		 * 	success:function(){//非必须
		 * 
		 * 	},
		 * 	error:function(){//错误，非必须
		 * 
		 * 	},
		 * 	login:function(){//登录逻辑，非必须
		 * 
		 * 	},
		 * 	done:function(){//一定会执行的方法
		 * 
		 * 	
		 * 	}
    	 * }
    	 * 
    	 * 1、var formData = new FormData();
    	 * formData.append(key,value);
    	 * 
    	 * 2、var formData = new FormData(ducouemntFormObj);
    	 */
    	ajaxForm : function(params){

    		mui('body').progressbar({
				progress: undefined
			}).show();
			$('body').find(".mui-progressbar").show();
			
    		var data = {};
    		if(params.data){
    			data = params.data;
    		}
    		if(!params.contentType){
    			params.contentType = false;
    		}
    		$.ajax({
    			url:params.url,
				type: 'POST',
				cache: false,
    			data:params.data,
				processData: false,
				contentType: params.contentType,
				dataType:"json",
    			success:function(data){

    				AjaxHelper.successDel(params,data);
    				
    			},
    			error:function(msg){

    				AjaxHelper.errorDel(params,msg);
    				
    			}
    			
    		});
    		
    	},
    	/**
    	 * 辅助请求报错处理
    	 * msg:ajax请求error原参数
    	 */
    	ajaxErrorMsgDel : function(msg){
    		
        	var responseText = msg.responseText;
        	var reg = /on:\W{0,}\w{0,}\S{0,}\s{0,}at/;
        	var errorInfos = responseText.match(reg);
        	var errorInfo = responseText;
        	if(errorInfos != null){
        		
        		errorInfo = errorInfos[0];
        		
        	}
        	if(errorInfo.indexOf("登录")>-1){//返回了重新登录页面
    			mui.toast("未登录或登录超时！");
    			top.location.href=AjaxHelper.urlLogin;
				return;
			}
        	return "error "+errorInfo+" there ！";
        },
        /**
         * 错误处理
         */
        errorDel:function(params,msg){
        	
        	var data = {};
        	//隐藏进度条
			$('body').find(".mui-progressbar").hide();
			
			msg = AjaxHelper.ajaxErrorMsgDel(msg);
			data[AjaxHelper.codeKey] = "error";
			data[AjaxHelper.msgKey] = msg;
			
			if(params.error){
				params.error(data);
			}else{
				
				top.layer.open({
					  icon:5,
					  title: "错误啦！"
					  ,content: AjaxHelper.ajaxErrorMsgDel(msg)
					}); 
				
			}
			if(params.done){
				params.done();
			}
        	
        },
		/**
		 * 成功返回处理
		 * success 成功回调方法
		 * data 返回数据集
		 */
    	successDel : function(params,data){
    		

			//隐藏进度条 原生的当请求太快时并能隐藏掉！！
			//mui('body').progressbar().hide();
			//隐藏进度条
			$('body').find(".mui-progressbar").hide();

			if(params.done){
				params.done(data);
			}
    		
    		if((typeof data)=="string" && data.indexOf("登录")>-1){//返回了重新登录页面
    			mui.toast("未登录或登录超时！");
    			top.location.href=AjaxHelper.urlLogin;
				return;
			}
    		if(data.code == undefined || data.code == ""){//非正规的数据返回，直接返回当前数据
				if(params.success){
					params.success(data);
				}
			}else{
				
				var code = data[AjaxHelper.codeKey];
				if(code == AjaxHelper.codeSuccess){//返回成功，直接返回数据集
					
					if(params.success){
						params.success(data.data);
					}
					
				}else{//返回错误码
					
					if(code == AjaxHelper.codeNeedLogin){//需要登录
						
						if(params.login){
							params.login();
							return;
							
						}
						
					}
					
					var msg = data[AjaxHelper.msgKey];
					
					if(params.error){
						
						params.error(data);
						
					}else{
						
						mui.toast(msg);
						
					}
					
				}
				
			}
    	},
        /**
         * url 文件下载地址
         * data 参数
         * body 当前页面的body
         */
        downloadFileByForm : function (params) {
			
	        var url = params.url;//文件下载地址
	        var data = params.data;//参数
	        var $body = params.body;//当前页面的body
	        
        	var form = $("<form></form>").attr("action", url).attr("method", "post");
        	
        	if(!data){
        		data = {};
        	}
        	if(!$body){
        		$body = $("body");
        	}
	        for(var key in data){
	        	
	        	var value = data[key];
	        	form.append($("<input></input>").attr("type", "hidden").attr("name", key).attr("value", value));
	        	
	        }
	        $body.append(form);
	        form.submit().remove();
	    }
}
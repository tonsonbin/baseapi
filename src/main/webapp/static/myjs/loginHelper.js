/**
* 登录工具
*/
LoginHelper = {
		
		/**
		 * 预配置数据》》
		 */
		//登录接口地址
		loginUrl:ctapi+"/nc/login/unauth/do",
		loginParams:{
			
			"mobileKey":"mobile",//设置登录请求参数中手机号的参数名
			"phoneVerifyCodeKey":"phoneVerifyCode"//设置登录请求参数中手机验证码的参数名
			
		},
		//登录token校验地址
		verifyLoginUrl:ctapi+"/nc/login/verifyLogin",//ExtAsk中请求参数统一封入了token，因此这个校验方法中不需要定义参数
		//短信验证码发送地址
		sendVerifyCodeUrl:ctapi+"/nc/sms/unauth/sendVerifyCode",
		sendVerifyCodeParams:{
			
			"mobileKey":"mobile",//设置请求参数中手机号的参数名
			"typeKey":"type",//设置请求参数中发送验证码类型的参数名
			"typeValue":"N01"//设置请求参数中发送验证码该类型的类型值（一般一个系统中会存在多个地方发送验证码，这里设置登录的类型值）
			
		},
		/**
		 * 预配置数据《《
		 */
		
		
		//记录调登录后的回调事件，如果有多个接口同时调了登录接口，那么这些接口都会记录到其中，当登录成功后会全部调用一次
    	callback:{},
    	
    	//登录的页面
    	content:
    	    '<div class="lk_tankuang" id="LL_LOGIN_BOX">'+
            '<div class="lk_tankuang_box">'+
                '<div class="lk_top" style="z-index:9999999">'+
                    '<span class="a">登录</span>'+
                    '<span class="b"><img class="b_img" onclick="javascript:LoginHelper.closeLogin()" src="'+ctxStatic+'/myjs/login/image/x.png" alt=""></span>'+
                '</div>'+
                '<ul class="lk_tankuang_ul">'+
                    '<li class="lk_tankuang_li">'+
                        '<span class="a"><img class="a_img" src="'+ctxStatic+'/myjs/login/image/sj.png" alt=""></span>'+
                        '<span class="b" id="vPhone"><input jq_verify="手机号码" name="phoneNumber" class="b_input" type="text" placeholder="请填写联系手机号码"></span>'+
                    '</li>'+
                    /*'<li class="lk_tankuang_li">'+
                        '<span class="a"><img class="a_img" src="'+ctxStatic+'/myjs/login/image/sj_2.png" alt=""></span>'+
                        '<span class="b"><input class="b_input" type="text" placeholder="请填写身份证号码"></span>'+
                    '</li>'+*/
                    /*'<li class="lk_tankuang_li  lk_tankuang_li_b">'+
                        '<span class="left">'+
                            '<span class="a"><img class="a_img" src="'+ctxStatic+'/myjs/login/image/sj_3.png" alt=""></span>'+
                            '<span class="b bb"><input class="b_input" type="text" placeholder="请填写图形验证码"></span>'+
                        '</span>'+
                        '<span class="right"><img class="right_img" src="'+ctxStatic+'/myjs/login/image/yz.png" alt=""></span>'+
                    '</li>'+*/
                    '<li class="lk_tankuang_li  lk_tankuang_li_b">'+
                        '<span class="left">'+
                            '<span class="a"><img class="a_img" src="'+ctxStatic+'/myjs/login/image/sj_3.png" alt=""></span>'+
                            '<span id="verifyCode" class="b bb"><input jq_verify="验证码" name="verifyCode" class="b_input" type="text" placeholder="请填写验证码"></span>'+
                        '</span>'+
                        '<span class="right" onclick="LoginHelper.sendVerifyCode(this)">获取验证码</span>'+
                    '</li>'+
                '</ul>'+
                /*'<span class="cuowu">错误提示内容</span>'+*/
                '<div class="bottom"><a class="tiaozhuan_a" href="javascript:LoginHelper.doLogin()">确认</a></div>'+
            '</div>'+
        '</div>',
        
        	//显示登录
    		showLogin:function(url,callback){
    			
    			LoginHelper.callback[url] = callback;
    			//如果没有则添加
    			var LL_LOGIN_BOXObj = $("body").find("#LL_LOGIN_BOX");
    			if(!LL_LOGIN_BOXObj || LL_LOGIN_BOXObj.length == 0){
    				$("body").append(LoginHelper.content);
    			}
    		},
    		
    		//关闭登录
    		closeLogin:function(url,callback){
    			
    			$("body").find("#LL_LOGIN_BOX").remove();
    			
    		},
    		
    		//进行登录操作
    		doLogin:function(){
    			
    			var cBObject = $("#LL_LOGIN_BOX");
    			//校验参数
    			var jvRes = TB_JUDGE.verify(cBObject,{
    				
    				callBack:function(obj,msg){
    					mui.toast(msg);
    				}
    				
    			});
    			if(!jvRes){
    				return;
    			}
    			
    			//调用登录接口
    			var phoneNumber = cBObject.find("input[name=phoneNumber]").val();
    			var verifyCode = cBObject.find("input[name=verifyCode]").val();
    			//请求参数组装
    			var data = {};
    			data[LoginHelper.loginParams.mobileKey] = phoneNumber;
    			data[LoginHelper.loginParams.phoneVerifyCodeKey] = verifyCode;
    			
    			ExtAsk.askSyn({
    				
    				"url":LoginHelper.loginUrl,
    				"data":data,
    				"success":function(data){
    					
    					//登录成功，记录token
    					LSHelper.put(LSHelper.token,data);
    					
    					for(var key in LoginHelper.callback){
    						var cb = LoginHelper.callback[key];
    						cb();
    						delete LoginHelper.callback[key];
    					}
    					LoginHelper.closeLogin();
    				},
    				"done":function(){
    					
    					//LoginHelper.closeLogin();
    					
    				}
    				
    			});
    		}
    		
    		//校验登录有效性
    		,verifyLogin:function(callback){
    			
    			ExtAsk.ask({
    				
    				url:LoginHelper.verifyLoginUrl,
    				success:function(data){
    					
    					//校验登录成功
    					if(callback){
    						callback();
    					}
    					
    				}
    				
    			});
    			
    		}
    		
    		//发送手机验证码
    		,sendVerifyCode:function(obj){
    			
    			var cBObject = $("#vPhone");
    			//校验参数
    			var jvRes = TB_JUDGE.verify(cBObject,{
    				
    				callBack:function(obj,msg){
    					mui.toast(msg);
    				}
    				
    			});
    			if(!jvRes){
    				return;
    			}
    			
    			//添加按钮倒计时
    			var trans = LoginHelper.trans;
    			if(!trans){
    				trans = new Trans();
    				LoginHelper.trans = trans;   			
    			} 			
    			if(trans.run){//正在运行
	    			
	    			mui.toast("点击过快");
	    			return;
	    		
	    		}else{
	    			
	    			trans.start(obj,60);
	    			
	    		}
    			
    			//调用发送验证码接口
    			var phoneNumber = cBObject.find("input[name=phoneNumber]").val();
    			//请求参数组装
    			var data = {};
    			data[LoginHelper.sendVerifyCodeParams.mobileKey] = phoneNumber;
    			data[LoginHelper.sendVerifyCodeParams.typeKey] = LoginHelper.sendVerifyCodeParams.typeValue;
    			
    			ExtAsk.askSyn({
    				
    				url:LoginHelper.sendVerifyCodeUrl,
    				data:data,
    				success:function(data){
    					
    					//发送成功
    					mui.toast("发送成功！");
    				}
    				
    			});
    			
    		}
    		
    }
    /**
     * ajax 扩展请求工具类
     * 
     * 自定义一些与项目相关的处理，比如所有请求都加入token参数
     * 
     */
    ExtAsk = {
    		
    		/**
    		 * 参数预处理
    		 */
    		paramsPre:function(params){
    			
    			if(!params.data){
    				params.data = {};
    			}
    			params.data.appKey = "0";
    			params.data.token = LSHelper.get(LSHelper.token);
    			
    			return params;
    		},
    		/**
    		 * 参数预处理
    		 */
    		paramsPreForm:function(params){
    			
    			if(!params.data){
    				params.data = AjaxHelper.formData();
    			}
    			params.data.append("appKey","0");
    			params.data.append("token",LSHelper.get(LSHelper.token));
    			
    			return params;
    		},
    		/**
    		 * {
    		 * url:"",
    		 * data:{},
    		 * success:function(){},
    		 * error:function(){}
    		 * 
    		 * }
    		 */
    		ask:function(params){
    			
    			params = ExtAsk.paramsPre(params);
    			
    			AjaxHelper.ajax({
    				
    				url:params.url,
    				data:params.data,
    				success:params.success,
    				error:params.error,
    				login:function(){
    					
    					Login.showLogin(params.url,function(){
    						ExtAsk.ask(params);
    					});
    					
    				},
    				done:params.done
    				
    			});
    			
    		},
    		/**
    		 * 表单数据提交
    		 * {
    		 * url:"",
    		 * data:{},
    		 * success:function(){},
    		 * error:function(){}
    		 * 
    		 * }
    		 */
    		ajaxForm:function(params){

    			params = ExtAsk.paramsPreForm(params);
    			
    			AjaxHelper.ajaxForm({
    				
    				url:params.url,
    				data:params.data,
    				success:params.success,
    				error:params.error,
    				login:function(){
    					
    					Login.showLogin(params.url,function(){
    						ExtAsk.ask(params);
    					});
    					
    				},
    				done:params.done
    				
    			});
    			
    		},
		    /**
		     * 同步请求
			 * {
			 * url:"",
			 * data:{},
			 * success:function(){},
			 * error:function(){}
			 * 
			 * }
			 */
			askSyn:function(params){

    			params = ExtAsk.paramsPre(params);
				
				AjaxHelper.ajaxSyn({
					
					url:params.url,
					data:params.data,
					success:params.success,
					error:params.error,
					login:function(){
						
						Login.showLogin(params.url,function(){
							ExtAsk.ask(params);
						});
						
					},
    				done:params.done
					
				});
				
			}
    		
    }
/*其中还可以改进，将initShareAppMessage放如一个Object
 * 
 * 注意不能主动控制弹出分享菜单，只能用户主动点击右上角的按钮
 * 
 * 页面
 * var loUrl=window.location.href;
	var initShareAppMessage={
		"title":"goods1",    // 分享标题                            
		"desc":"商品。。。。。",     // 分享描述                            
		"link":url,     // 分享链接                            
		"imgUrl":"images/mess.png",   // 分享图标，图片这里要注意用完整的url地址                            
		"type":"link",     // 分享类型,music、video或link，不填默认为link 
		"dataUrl":"",  // 如果type是music或video，则要提供数据链接，默认为空
		"success": function(){}, // 确认分享回调函数
        "cancel":function(){}    // 取消分享的回调函数
};
initConfigWx(initShareAppMessage,loUrl);
//2016-11-03 微信分享qq，朋友圈，微信好友、空间可以进行，基本功能包含
 */

function initConfigWx(actionAddress,obj,loUrl){
	
	$.ajax({
		url:actionAddress,
		data:{
			"url":loUrl
		},
		success:function(data){console.log(data);
			var jsonres=data.data;
			wx.config({
			    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			    appId: jsonres.appId, // 必填，公众号的唯一标识
			    timestamp: jsonres.timestamp, // 必填，生成签名的时间戳
			    nonceStr: jsonres.nonceStr, // 必填，生成签名的随机串
			    signature: jsonres.signature,// 必填，签名，见附录1
			    jsApiList: ['checkJsApi',
			                'onMenuShareTimeline',
			                'onMenuShareAppMessage',
			                "showMenuItems",
			                "hideOptionMenu",
			                "hideMenuItems",
			                "onMenuShareQQ",
			                "onMenuShareQZone",
			                "onMenuShareWeibo",
			                "updateTimelineShareData",
			                "updateAppMessageShareData"
			                ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
			});
		},
		error:function(){
			alert("weixin share err");
		}
	});
	wx.ready(function(){
		console.log("初始化菜单");
		if(obj!=null){
			//隐藏按钮
			//wx.hideOptionMenu({success:function(res){
		    //     //alert("隐藏");
		    // },fail:function(res){
		    // 	//alert(JSON.stringify(res));
		    // },trigger:function(){
		    // 	//alert(JSON.stringify(res));
		    // },cancel:function(){
		    // 	//alert(JSON.stringify(res));
		    // },complete:function(){
		    // 	//alert(JSON.stringify(res));
		    // }
		    //});
			//设置所有的非基础类菜单
			//wx.hideAllNonBaseMenuItem();
			//设置显示的功能性菜单
			//wx.showMenuItems({
			 //menuList: ["menuItem:share:appMessage","menuItem:share:timeline"]
			//,success:function(res){
	            //alert("隐藏");
	        //},fail:function(res){
	        	//alert(JSON.stringify(res));
	        //},trigger:function(){
	        	//alert(JSON.stringify(res));
	        //},cancel:function(){
	        	//alert(JSON.stringify(res));
	        //},complete:function(){
	        	//alert(JSON.stringify(res));
	        //}
			//});
			//隐藏功能性菜单
			/*wx.hideMenuItems({
	                menuList: [//'menuItem:share:qq',
	                           //'menuItem:share:weiboApp',
	                           //'menuItem:favorite',
	                           //'menuItem:share:facebook',
	                           //'menuItem:share:QZone'
	                           ], // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
	                success:function(res){
	                    //alert("隐藏");
	                },fail:function(res){
	                	//alert(JSON.stringify(res));
	                },trigger:function(){
	                	//alert(JSON.stringify(res));
	                },cancel:function(){
	                	//alert(JSON.stringify(res));
	                },complete:function(){
	                	//alert(JSON.stringify(res));
	              	}
	        });*/
			//分享到朋友圈
		    /*wx.onMenuShareTimeline({//旧版本将废弃
		        title: obj.title,
		        link: obj.link,
		        imgUrl: obj.imgUrl,
		        trigger: function (res) {
		            //alert('用户点击分享到朋友圈');
		        },
		        success: function (res) {
		           //alert('已分享');
		        },
		        cancel: function (res) {
		           //alert('已取消');
		        },
		        fail: function (res) {
		           //alert('wx.onMenuShareTimeline:fail: '+JSON.stringify(res));
		        }
		    });*/
		    wx.updateTimelineShareData({
		    	title: obj.title,
		        link: obj.link,
		        imgUrl: obj.imgUrl,
		        success: function (res) {
			           //alert('已分享');
			        }
		    	
		    })
		  //分享给微信好友
		    /*wx.onMenuShareAppMessage({
		        title: obj.title, 		// 分享标题
		        desc: obj.desc, 		// 分享描述
		        link: obj.link,   // 分享链接
		        imgUrl: obj.imgUrl, 		// 分享图标
		        type: obj.type, 		// 分享类型,music、video或link，不填默认为link
		        dataUrl: obj.dataUrl, 		// 如果type是music或video，则要提供数据链接，默认为空
		        success: function(){
		        	//alert("d");
		        },
		        cancel:function(){
		        	//alert("c");
		        }
		    });*/
		    updateAppMessageShareData({
		        title: obj.title, 		// 分享标题
		        desc: obj.desc, 		// 分享描述
		        link: obj.link,   // 分享链接
		        imgUrl: obj.imgUrl, 		// 分享图标
		        type: obj.type, 		// 分享类型,music、video或link，不填默认为link
		        dataUrl: obj.dataUrl, 		// 如果type是music或video，则要提供数据链接，默认为空
		        success: function(){
		        	//alert("d");
		        },
		        cancel:function(){
		        	//alert("c");
		        }
		    });
		    //分享到qq
		    /*wx.onMenuShareQQ({
		    	title: obj.title, 		// 分享标题
		        desc: obj.desc, 		// 分享描述
		        link: obj.link,   // 分享链接
		        imgUrl: obj.imgUrl, 		// 分享图标
		        type: obj.type, 		// 分享类型,music、video或link，不填默认为link
		        dataUrl: obj.dataUrl, 		// 如果type是music或video，则要提供数据链接，默认为空
		        success: function(){
		        	//alert("d");
		        },
		        cancel:function(){
		        	//alert("c");
		        }
		    });*/
		    
		    //分享到腾讯微博
		    wx.onMenuShareWeibo({
		    	title: obj.title, 		// 分享标题
		        desc: obj.desc, 		// 分享描述
		        link: obj.link,   // 分享链接
		        imgUrl: obj.imgUrl, 		// 分享图标
		        type: obj.type, 		// 分享类型,music、video或link，不填默认为link
		        dataUrl: obj.dataUrl, 		// 如果type是music或video，则要提供数据链接，默认为空
		        success: function(){
		        	//alert("d");
		        },
		        cancel:function(){
		        	//alert("c");
		        }
		    });
		  //分享到qq空间
		    /*wx.onMenuShareQZone({
		    	title: obj.title, 		// 分享标题
		        desc: obj.desc, 		// 分享描述
		        link: obj.link,   // 分享链接
		        imgUrl: obj.imgUrl, 		// 分享图标
		        type: obj.type, 		// 分享类型,music、video或link，不填默认为link
		        dataUrl: obj.dataUrl, 		// 如果type是music或video，则要提供数据链接，默认为空
		        success: function(){
		        	//alert("d");
		        },
		        cancel:function(){
		        	//alert("c");
		        }
		    });*/
		}
	});
	wx.error(function(res){
		alert("config is err"+res.errMsg);
	});
};
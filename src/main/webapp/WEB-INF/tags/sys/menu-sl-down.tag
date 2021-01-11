<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="标识该menu-sl-down的唯一标识"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="自定义样式"%>
<%@ attribute name="fontStyle" type="java.lang.String" required="false" description="自定义文字样式"%>

<style>
			.menu-sl-down-animated {
				-webkit-animation-duration: 0.5s;
				animation-duration: 0.5s;
				-webkit-animation-fill-mode: both;
				animation-fill-mode: both;
			}
			
			.menu-sl-down-bounce-in-down {
				-webkit-animation-name: menu-sl-down-bounceOutUp;
				animation-name: menu-sl-down-bounceInDown;
			}
			
			.menu-sl-down-fade-in-down {
				-webkit-animation-name: menu-sl-down-bounceInDown;
				animation-name: menu-sl-down-fadeInDown;
			}
			
			.menu-sl-down-bounce-out-up {
				-webkit-animation-name: menu-sl-down-bounceOutUp;
				animation-name: menu-sl-down-bounceOutUp;
			}
			
			.menu-sl-down-fade-out-up {
				-webkit-animation-name: menu-sl-down-fadeOutUp;
				animation-name: menu-sl-down-fadeOutUp;
			}
			.menu-sl-down-menu-open .mui-scroll-wrapper {
				position: absolute;
				top: 44;
				bottom: 0;
				left: 0;
				z-index: 1;
				width: 100%;
				overflow: hidden;
				-webkit-backface-visibility: hidden;
			}
			.menu-sl-down-menu-wrapper {
				position: fixed;
				top: 44px;
				left: 0;
				right: 0;
				z-index: 900;
				text-align: center;
				background-color: #333;
				width: 100%;
				opacity:0.8;
			}
			.menu-sl-down-menu-wrapper.hidden {
				-webkit-transform: translate3d(0, -100%, 0);
				transform: translate3d(0, -100%, 0);
				z-index: -1;
			}
			.menu-sl-down-menu-wrapper .menu {
				width: 100%;
			}
			.menu-sl-down-menu-wrapper .menu .mui-table-view-inverted {
				color: gray;
				font-size: 19px;
			}
			.menu-sl-down-menu-wrapper .menu .mui-table-view-inverted .mui-table-view-cell:after {
				height: 2px;
				left: 0;
				right: 0;
			}
			.menu-sl-down-menu-wrapper.menu-sl-down-mui-active,
			.menu-sl-down-menu-wrapper.menu-sl-down-mui-active .menu {
				-webkit-transform: translate3d(0, 0, 0);
				transform: translate3d(0, 0, 0);
			}
			
			
			@-webkit-keyframes menu-sl-down-bounceInDown {
				0%, 60%, 75%, 90%, 100% {
					-webkit-transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
					transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
				}
				0% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
				60% {
					opacity: 1;
					-webkit-transform: translate3d(0, 25px, 0);
					transform: translate3d(0, 25px, 0);
				}
				75% {
					-webkit-transform: translate3d(0, -10px, 0);
					transform: translate3d(0, -10px, 0);
				}
				90% {
					-webkit-transform: translate3d(0, 5px, 0);
					transform: translate3d(0, 5px, 0);
				}
				100% {
					-webkit-transform: none;
					transform: none;
				}
			}
			@keyframes menu-sl-down-bounceInDown {
				0%, 60%, 75%, 90%, 100% {
					-webkit-transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
					transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
				}
				0% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
				60% {
					opacity: 1;
					-webkit-transform: translate3d(0, 25px, 0);
					transform: translate3d(0, 25px, 0);
				}
				75% {
					-webkit-transform: translate3d(0, -10px, 0);
					transform: translate3d(0, -10px, 0);
				}
				90% {
					-webkit-transform: translate3d(0, 5px, 0);
					transform: translate3d(0, 5px, 0);
				}
				100% {
					-webkit-transform: none;
					transform: none;
				}
			}
			@-webkit-keyframes menu-sl-down-fadeInDown {
				0%, 60%, 75%, 90%, 100% {
					-webkit-transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
					transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
				}
				0% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
				60% {
					opacity: 1;
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				75% {
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				90% {
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				100% {
					-webkit-transform: none;
					transform: none;
				}
			}
			@keyframes menu-sl-down-fadeInDown {
				0%, 60%, 75%, 90%, 100% {
					-webkit-transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
					transition-timing-function: cubic-bezier(0.215, 0.610, 0.355, 1.000);
				}
				0% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
				60% {
					opacity: 1;
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				75% {
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				90% {
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				100% {
					-webkit-transform: none;
					transform: none;
				}
			}
			@-webkit-keyframes menu-sl-down-bounceOutUp {
				20% {
					-webkit-transform: translate3d(0, -10px, 0);
					transform: translate3d(0, -10px, 0);
				}
				40%,
				45% {
					opacity: 1;
					-webkit-transform: translate3d(0, 20px, 0);
					transform: translate3d(0, 20px, 0);
				}
				100% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
			}
			@keyframes menu-sl-down-bounceOutUp {
				20% {
					-webkit-transform: translate3d(0, -10px, 0);
					transform: translate3d(0, -10px, 0);
				}
				40%,
				45% {
					opacity: 1;
					-webkit-transform: translate3d(0, 20px, 0);
					transform: translate3d(0, 20px, 0);
				}
				100% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
			}
			@-webkit-keyframes menu-sl-down-fadeOutUp {
				20% {
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				40%,
				45% {
					opacity: 1;
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				100% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
			}
			@keyframes menu-sl-down-fadeOutUp {
				20% {
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				40%,
				45% {
					opacity: 1;
					-webkit-transform: translate3d(0, 0px, 0);
					transform: translate3d(0, 0px, 0);
				}
				100% {
					opacity: 0;
					-webkit-transform: translate3d(0, -100%, 0);
					transform: translate3d(0, -100%, 0);
				}
			}
		</style>
<div id="menu-sl-down-${id }" class="menu-sl-down-menu-wrapper hidden" style="${cssStyle}">
	<div id="menu-sl-down-menu-${id }" class="menu">
		<ul id="menu-sl-down-menu-ul-${id }" class="mui-table-view mui-table-view-inverted">
			
			
			
		</ul>
	</div>
</div>

<script id="tpl-menu-sl-down-${id}" type="text/html">

	{{each list as item index}}
    	<li class="mui-table-view-cell">
			<a index="{{index}}" href="javascript:;" style="${fontStyle}">{{item[itemNameKey]}}</a>
		</li>
	{{/each}}

</script>

<script type="text/javascript">

//构建slider
var buildMenuSLDown = function(params){
	
	var log = new LogHelper({"show":true});
	log.init({
		"className":"buildMenuSLDown"
	});
	log.append("参数",params);
	
	if(!params){
		
		params = {};
		
	}
	
	var id = params.id;
	var data = params.data;
	var click = params.click;
	var itemNameKey = params.itemNameKey;
	
	//参数判断
	if(!TB_JUDGE.notNull(id)){
		log.append("方法调用失败","id为空！");
		log.print();
		return;
	}
	if(!TB_JUDGE.notNull(data)){
		log.append("方法调用失败","data为空！");
		log.print();
		return;
	}
	if(!TB_JUDGE.notNull(itemNameKey)){
		log.append("方法调用失败","itemNameKey为空！");
		log.print();
		return;
	}
	
	//页面渲染数据
	$("#menu-sl-down-menu-ul-"+id).html(template("tpl-menu-sl-down-"+id,{"list":data,"itemNameKey":itemNameKey}));

	if(click){
		
		//回调事件
		$("#menu-sl-down-menu-ul-"+id).on('tap', 'a', function (event) {
			
			var index = $(this).attr("index");
			var iData = data[parseInt(index)];
			
			//隐藏菜单
			menuSLDown.toggle({
				
				"id":id
				
			});
			
			click(iData);
	         				
		});

	}
	
	log.append("方法调用完成");
	log.print();
}

/**
 *
 */
var menuSLDown = {
	
	"busying":{},//是否动画进行中
	
	/**
	数据刷新
	 params：
	 {
			
			"id":""
			,"data":[]
			,"itemNameKey":""//item名称的key
			,"click":function(itemData){}//当前元素对应的列表的值
			
		}
	**/
	"reresh":function(params){

		 buildMenuSLDown(params);
		
	},
	/**
	隐藏/显示
	{
		"id":
	}
	**/
	"toggle":function(params){
		
		var id = params.id;
		
		if (menuSLDown.busying[id] == "true") {
			return;
		}
		
		var menuWrapper = document.getElementById("menu-sl-down-"+id);
		var menu = document.getElementById("menu-sl-down-menu-"+id);
		var menuWrapperClassList = menuWrapper.classList;
		
		menuSLDown.busying[id] = "true";
		if (menuWrapperClassList.contains('menu-sl-down-mui-active')) {
			document.body.classList.remove('menu-sl-down-menu-open');
			menuWrapper.className = 'menu-sl-down-menu-wrapper menu-sl-down-fade-out-up menu-sl-down-animated';
			menu.className = 'menu menu-sl-down-bounce-out-up menu-sl-down-animated';
			setTimeout(function() {
				//backdrop.style.opacity = 0;
				menuWrapper.classList.add('hidden');
			}, 500);
		} else {
			document.body.classList.add('menu-sl-down-menu-open');
			menuWrapper.className = 'menu-sl-down-menu-wrapper menu-sl-down-fade-in-down menu-sl-down-animated menu-sl-down-mui-active';
			menu.className = 'menu menu-sl-down-bounce-in-down menu-sl-down-animated';
			//backdrop.style.opacity = 1;
		}
		setTimeout(function() {
			menuSLDown.busying[id] = "false";
		}, 500);
		
	}
		 
 }
</script>
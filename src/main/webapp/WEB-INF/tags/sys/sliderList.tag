<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="标识该slider的唯一标识"%>

<div id="sw-slider-${id}" class="swiper-container">
	<div  id="sw-slider-i-${id}" class="swiper-wrapper">
	</div>
	<!-- 如果需要分页器 -->
	<div class="swiper-pagination"></div>
</div>

<script id="tpl-sw-slider-${id}" type="text/html">

	{{each list as item index}}
    <div class="swiper-slide"><image src="{{item[picUrlKey]}}"></div>
	{{/each}}

</script>

<script type="text/javascript">

//构建slider
var buildslider = function(params){
	
	var log = new LogHelper({"show":true});
	log.init({
		"className":"buildslider"
	});
	log.append("参数",params);
	
	if(!params){
		
		params = {};
		
	}
	
	var id = params.id;
	var data = params.data;
	var picUrlKey = params.picUrlKey;
	
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
	if(!TB_JUDGE.notNull(picUrlKey)){
		log.append("方法调用失败","picUrlKey为空！");
		log.print();
		return;
	}
	
	$("#sw-slider-i-"+id).html(template("tpl-sw-slider-"+id,{"list":data,"picUrlKey":picUrlKey}));

	var cswiper = document.querySelector("#sw-slider-"+id).swiper;
	if(cswiper){
		
		cswiper.destroy();
		
	}
	var mySwiper = new Swiper("#sw-slider-"+id,{
		loop:true,
	    speed: 500,
	    autoplay:{
	        delay: 2500,
	        disableOnInteraction: false,
	    },
	    //拖动释放时不会输出信息，阻止click冒泡。拖动Swiper时阻止click事件。
	    preventLinksPropagation:true,
	    pagination: {
	        el: '.swiper-pagination'
	      }
	});

	log.append("方法调用完成");
	log.print();
}
//刷新slider
/**
 * params：
 {
		
		"id":""
		,"data":[]
		,"picUrlKey":""//图片地址的key
		
	}
 */
var sliderReresh = function(params){

	 buildslider(params);
	
}

</script>
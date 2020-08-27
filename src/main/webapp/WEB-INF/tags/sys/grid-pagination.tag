<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="false" description="id"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="自定义样式"%>

<div id="grid-pagination-${id}" class="mui-slider" style='margin-top:15px;${empty cssStyle?"":cssStyle}' >
	<div class="mui-slider-group">
		
		

	</div>
	<div id="grid-pagination-indicator-${id}" class="mui-slider-indicator">
		
	</div>
</div>

<style>


</style>

<script id="tpl-grid-pagination-${id}" type="text/html">

<div class="mui-slider-item">
			<ul class="mui-table-view mui-grid-view mui-grid-9" style="background:transparent">
			{{each list as item index}}
				<li class="mui-table-view-cell mui-media mui-col-xs-{{colspan}} mui-col-sm-{{colspan}}" dataIndex="{{startIndex+index}}">
					<a href="#">
						<span class="mui-icon"><img src="{{item[itemPicUrlKey]}}"/></span>
						<div class="mui-media-body">{{item[itemNameKey]}}</div>
					</a>
				</li>
			{{/each}}
			</ul>
		</div>
</script>
<script id="tpl-grid-pagination-indicator-${id}" type="text/html">
	<div class="mui-indicator {{if pageNo == 1}}mui-active{{/if}}" ></div>
</script>

<script type="text/javascript">

//构建slider
var buildGridPagination = function(params){
	
	var log = new LogHelper({"show":true});
	log.init({
		"className":"buildHeader"
	});
	log.append("参数",params);
	
	if(!params){
		
		params = {};
		
	}
	
	var id = params.id;
	//数据列表
	var list = params.list;
	//item的name的key
	var itemNameKey = params.itemNameKey;
	//itemName的picUrl的key
	var itemPicUrlKey = params.itemPicUrlKey;
	//显示行数
	var rows = params.rows;
	//显示的列数
	var cols = params.cols;
	//点击事件
	var click = params.click;
	
	if(!list || list.length == 0){
		return;
	}
	if(!rows){
		rows = 2;
	}
	if(!cols){
		cols = 4;
	}
	
	//计算有几页
	var count = list.length;
	var pageNo = Math.round(count/(cols*rows));
	//计算colspan
	var colspan = Math.round(12/cols);
	//数据渲染
	var slider = $("#grid-pagination-"+id);
	var group = slider.find('.mui-slider-group');
	//先清空内容
	group.empty();
	$("#grid-pagination-indicator-"+id).empty();
	
	//渲染主内容
	for(var index=1; index<=pageNo; index++){
		//开始索引
		var startIndex = (index-1)*(cols*rows);
		
		var listItem = list.slice(startIndex,index*(cols*rows));
		
		group.append(template("tpl-grid-pagination-"+id,{"list":listItem,"itemNameKey":itemNameKey,"itemPicUrlKey":itemPicUrlKey,"startIndex":startIndex,"colspan":colspan}));

		//渲染标点，如果只有一页就不渲染标点
		if(pageNo != 1){

			$("#grid-pagination-indicator-"+id).append(template("tpl-grid-pagination-indicator-"+id,{"pageNo":index}));
			
		}
	}
	if(click){

		//点击事件
		group.find(".mui-table-view-cell").click(function(){
			
			var dataIndex = $(this).attr("dataIndex");
			click(list[parseInt(dataIndex)]);
			
		});
		
	}
	
	//初始化循环组件
	var items = group.find(".mui-slider-item");
	//克隆第一个节点
	var first = items.eq(0)[0].cloneNode(true);
	first.classList.add('mui-slider-item-duplicate');
	//克隆最后一个节点
	var last = items.eq(items.length - 1)[0].cloneNode(true);
	last.classList.add('mui-slider-item-duplicate');
	//处理是否循环逻辑，若支持循环，需支持两点：
	//1、在.mui-slider-group节点上增加.mui-slider-loop类
	//2、重复增加2个循环节点，图片顺序变为：N、1、2...N、1
	var sliderApi = mui(slider).slider();

	log.append("方法调用完成");
	log.print();
}
//刷新slider
/**
 * params：
 {
		"id":"",//tag标识
		"list":[],//数据列表
		"itemNameKey":'',//菜单名称对应列表中的名称key
		"itemPicUrlKey":'',//菜单图片对应列表中的图片key
		"click":function(data){},//点击事件
		"rows":2//每一页的行数，如果不传则默认两行
		"cols":4//每一页的列数只能2,3,4,6，如果不传则默认4列
		
	}
 */
var gridPaginationReresh = function(params){

	 buildGridPagination(params);
	
}

</script>
/**
 * tb
 * 用于将秒的时间刻度转换为时分秒的刻度
 * 例如还剩1232435434秒，转为还剩XX小时XX分XX秒
 */
//将秒计的时间转换为 xx天xx小时xx分xx秒的格式，
$(".transtime").each(function(){
	 var nowTime=transTime(parseInt(this.innerText));
	 this.innerText=nowTime;
 });
//将秒计的时间转换为 xx天xx小时xx分xx秒的格式，并且按秒倒计时
$(".openTime").each(function(){
	var sTime=parseInt(this.innerText);
	var obj=this;
	var nowTime=transTime(sTime);
	obj.innerText=nowTime;
	var interval=window.setInterval(function(){
		if(sTime<0){
			window.clearInterval(interval);
			return;
		}
		var nowTime=transTime(sTime);
		obj.innerText=nowTime;
		sTime=sTime-1;
	},1000);
});
/**
 * 点击进行倒计时事件
 * 示例
 * var trans = new Trans();
    	$("[jq_click=getCode]").click(function(){
    		
    		if(trans.run){//正在运行
    			
    			toast({msg:"点击过快"});
    		
    		}else{
    			
    			trans.start(this,30);
    			
    		}
    		
    	});
 */
var Trans=function(){
	this.run=false;
	var thisObj = this;
	/**
	 * documentObj 需要显示倒计时的文档元素
	 * sTime 倒计时的时长
	 * callback 倒计时完成后回调
	 */
	this.start=function(documentObj,sTime,callback){
		thisObj.run=true;
		var tagName = documentObj.tagName;
		var befStr=documentObj.innerText;
		if(tagName == "INPUT"){
			befStr=documentObj.value;
		}
		var obj=documentObj;
		var nowTime=transTime(sTime);
		if(tagName == "INPUT"){
			obj.value=nowTime;
		}else
			obj.innerText=nowTime;
		var interval=window.setInterval(function(){
			if(sTime<=0){
				window.clearInterval(interval);
				if(tagName == "INPUT"){
					obj.value=befStr;
				}else
					obj.innerText=befStr;
				thisObj.run=false;
				if(callback){
					callback();
				}
				return;
			}
			var nowTime=transTime(sTime);
			if(tagName == "INPUT"){
				obj.value=nowTime;
			}else
				obj.innerText=nowTime;
			sTime=sTime-1;
		},1000);
	};
};
//按倒计时显示当前时间
$(".nowTime").each(function(){
	var obj=this;
	var interval=window.setInterval(function(){
		var date=new Date();
		var day=date.getDate();
		var year=date.getUTCFullYear();
		var month=date.getMonth()+1;
		var hours=date.getHours();
		var minutes=date.getMinutes();
		var seconds=date.getSeconds();
		var weekDay=date.getUTCDay();
		obj.innerText=year+"年"+month+"月"+day+"日"+hours+":"+minutes+":"+seconds+" 星期"+weekDay;
		obj.style.color="#A1D6E8;";
		obj.style.fontFamily="Verdana, Arial, Helvetica, sans-serif;";
		obj.style.fontSize="12px;";
		},1000);
});

//将秒计的时间转换为天，时，分，秒的格式
function transTime(souTime){
	 var res="";
	 if(souTime>24*60*60){//在天以上
		 var day=Math.floor(souTime/(24*60*60));
	 	 souTime=souTime-day*24*60*60;
	 	 res+=day+"天";
	 }
	 if(souTime>60*60){//在小时以上
		 var hour=Math.floor(souTime/(60*60));
	 	 souTime=souTime-hour*60*60;
	 	 res+=hour+"小时";
	 }
	 if(souTime>60){//在分以上
		 var min=Math.floor(souTime/60);
	 	 souTime=souTime-min*60;
	 	 res+=min+"分";
	 }
	 res+=souTime+"秒";
	 return res;
}
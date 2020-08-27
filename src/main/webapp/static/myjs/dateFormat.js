
/**
 * 时间格式化工具
 * 
 * date 需要格式化的时间，默认当前时间
 * 
 * 调用方法
 * var d = new DateFormat();
 * d.format();
 * 
 * author tb
 * time 2019-05-09
 */
DateFormat = function(date,beDebug){
	
	if(beDebug){
		console.log("DateFormat>date="+date);
	}
	
	//将时间单元格式化为0x格式的
	this.formatUnit = function(unit){
		
		var reg = /^[0-9]{2,}$/;
		
		if(!reg.test(unit)){
			
			return "0"+unit;
			
		}else return unit;
	}
	
	if(date != null && date != undefined && date != ''){
		
		this.date = new Date(date);
		
	}else this.date = new Date();
	//console.log("DateFormat inited>date="+date);
	//非0x格式的
	this.year = this.date.getFullYear();
	this.year2 = (""+this.year).substring((""+this.year).length-2);
	this.month = this.date.getMonth()+1;
	this.day = this.date.getDate();
	
	this.hours = this.date.getHours();
	this.minutes = this.date.getMinutes();
	this.seconds = this.date.getSeconds();
	
	//0x格式的
	this.Fmonth = this.formatUnit(this.month);
	this.Fday = this.formatUnit(this.day);
	this.Fhours = this.formatUnit(this.hours);
	this.Fminutes = this.formatUnit(this.minutes);
	this.Fseconds = this.formatUnit(this.seconds);
	
	/**
	 * format 要格式化成的形式
	 * 
	 * 默认为 yyyy-MM-dd HH:mm:ss
	 * 
	 * yyyy或yy 年
	 * MM或M		月
	 * dd或d		日
	 * HH或H		时
	 * mm或m		分
	 * ss或s		秒
	 * 
	 */
	this.format = function(format){
		
		if(format == null || format == undefined || format == ""){
			
			format = "yyyy-MM-dd HH:mm:ss";
			
		}
		if(format.indexOf("yyyy") >= 0){
			
			format = format.replace("yyyy",this.year);
			
		}else if(format.indexOf("yy") >= 0){

			format = format.replace("yy",this.year2);
			
		}
		if(format.indexOf("MM") >= 0){
			
			format = format.replace("MM",this.Fmonth);
			
		}else if(format.indexOf("M") >= 0){

			format = format.replace("M",this.month);
			
		}
		if(format.indexOf("dd") >= 0){
			
			format = format.replace("dd",this.Fday);
			
		}else if(format.indexOf("d") >= 0){

			format = format.replace("d",this.day);
			
		}
		if(format.indexOf("HH") >= 0){
			
			format = format.replace("HH",this.Fhours);
			
		}else if(format.indexOf("H") >= 0){

			format = format.replace("H",this.hours);
			
		}
		if(format.indexOf("mm") >= 0){
			
			format = format.replace("mm",this.Fminutes);
			
		}else if(format.indexOf("m") >= 0){

			format = format.replace("m",this.minutes);
			
		}
		if(format.indexOf("ss") >= 0){
			
			format = format.replace("ss",this.Fseconds);
			
		}else if(format.indexOf("s") >= 0){

			format = format.replace("s",this.seconds);
			
		}
		
		return format;
	};
}

/**

 */
LogHelper = function(config){
	
	if(!config){
		config = {};
	}
	
	var show = config.show;
	
	var logInfo = [];
	
	this.functionName = "";
	
	var this_ = this;
	
	//初始化数据
	this.init = function(config){
		
		if(!config){
			config = {};
		}
		
		logInfo = [];
		
		var className = config.className;
		if(className){
			logInfo.push("方法名："+className);
		}
		
	}
	//添加日志数据
	this.append = function(desc,info){
		
		var infoj = {};
		infoj[desc] = info;
		logInfo.push(infoj);
		
	}
	//打印数据
	this.print = function(){
		
		if(show){
			console.log("连接："+window.location.href);
			console.log(logInfo);
		}
		
	}
}
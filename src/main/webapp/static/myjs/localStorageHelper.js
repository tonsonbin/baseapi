/**
* localStorage本地缓存工具
*/
LSHelper = {
	
	commonKey:"productName_ls_",
	//缓存的key
	//token
	"token":"token",
	
	//存值，会自动将对象转换为字符串形式
	put:function(key,value){
		
		if(value instanceof Object){
			value = JSON.stringify(value);
		}
		window.localStorage.setItem(LSHelper.commonKey+key,value);
		
	},
	
	//取值，自动将字符串转换为对象的形式
	get:function(key){
		
		var value = window.localStorage.getItem(LSHelper.commonKey+key);
		if(value != undefined){
			value = JSON.parse(value);
		}
		return value;
		
	}
	
}
//判断参数
/**
 * author：tb
 * 
 * 
 *
 */
TB_JUDGE ={
	/**
	 * 正则集
	 */
	regix_mess : {
			
		"idCard":{"mess":"身份证号码错误","reg":/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1−9]\d5\d2((0[1−9])|(10|11|12))(([0−2][1−9])|10|20|30|31)\d2[0−9Xx]$)|(^[1−9]\d5\d2((0[1−9])|(10|11|12))(([0−2][1−9])|10|20|30|31)\d2[0−9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/},
		"phone":{"mess":"手机号码错误","reg":/^1[34578]\d{9}$/},
		"integer":{"mess":"请输入正确数值","reg":/(^-{0,}[1-9]{1}[0-9]{0,}$)|(^0$)/},
		"float":{"mess":"请输入正确数值","reg":/(^-{0,}[0-9]{1,}\.[0-9]{1,}$)|(^-{0,}[1-9]{1}[0-9]{0,}$)|(^0$)/},
		"notNull":{"mess":"不能为空","reg":/\S/}
			
	},
	/**
	 * 是否是身份证号
	 */
	isIdCard : function(value){if(this.regix_mess["idCard"]["reg"].test(value)){return true;}else return false;},

	/**
	 * 是否是手机号
	 */
	isPhone : function(value){if(this.regix_mess["phone"]["reg"].test(value)){return true;}else return false;},
	/**
	 * 是否是整数型
	 */
	isInteger :  function(value){if(this.regix_mess["integer"]["reg"].test(value)){return true;}else return false;},
	/**
	 * 是否是浮点型
	 */
	isFloat :  function(value){if(this.regix_mess["float"]["reg"].test(value)){return true;}else return false;},
	/**
	 * 是否是不为空
	 */
	notNull :  function(value){if(this.regix_mess["notNull"]["reg"].test(value) && value != undefined && value != null){return true;}else return false;},
	
	/**
	 * 校验密码复杂度
	 * 
	 * value:校验值
	 * config:配置{//可为空，为空时做全校验
	 * 
	 * 	contains:"number,lowerLetters,upperLetters,symbols"//分别表示数字，小写字母，大写字母，符号，这里有的则值里面必须包含
	 * 	,minLength:8 //最小位数
	 * }
	 * 
	 * res:
	 * {
	 * state:boolean
	 * msg:string
	 * }
	 */
	verifyPassword :  function(value,config){
		
		var res = {};
		if(!config){
			config = {};
		}
		var contains = config.contains;
		if(!contains){//默认全校验
			contains = "number,lowerLetters,upperLetters,symbols";
		}
		var minLength = config.minLength;
		if(!minLength){//默认最少8位
			minLength = 8;
		}
		
		res.state = true;
		
		var regexNum = /\S{0,}[0-9]{1,}\S{0,}/;
		var regexLowerLetters = /\S{0,}[a-z]{1,}\S{0,}/;
		var regexUpperLetters = /\S{0,}[A-Z]{1,}\S{0,}/;
		var regexSpecialSymbols = /\S{0,}[`\~\!@\#\$\%\^\&\*\(\)\{\}\[\]\|:\"':,\.\<\>\?\/]{1,}\S{0,}/;
		
		if(!regexNum.test(value) && contains.indexOf("number")>-1){//是否有数字
			res.msg="必须包含数字";
			res.state = false;
			return res;
		}
		if(!regexLowerLetters.test(value) && contains.indexOf("lowerLetters")>-1){//是否有小写字母
			res.msg="必须包含小写字母";
			res.state = false;
			return res;
		}
		if(!regexUpperLetters.test(value) && contains.indexOf("upperLetters")>-1){//是否有大写字母
			res.msg="必须包含大写字母";
			res.state = false;
			return res;
		}
		if(!regexSpecialSymbols.test(value) && contains.indexOf("symbols")>-1){//是否有特殊符号
			res.msg="必须包含特殊符号";
			res.state = false;
			return res;
		}
		if(value.length < minLength){//是否符合位数
			res.msg="至少"+minLength+"位";
			res.state = false;
			return res;
		}
		
		return res;
	},
	/**
	 * 表单类型数据校验
	 * 使用说明
	 * 调用：TB_JUDGE.verify($obj,{});也可以直接TB_JUDGE.verify($obj);
	 * 
	 * 1、方法体
	 * 	$obj ： 任意的jq对象，对要单独校验的块用元素包裹，传入该元素的jq对象
	 * 	params：
	 * 	{
	 * 	callBack:function; 在发现有不符合的数据时回调（element obj，message），可以在回调中自定义提醒等操作
	 * 	verifyValue:string; 如果不传入此参数则表示全$obj块的校验，如果传入了，则只是校验jq_verify=verifyValue的元素
	 * 	}
	 * 	返回 boolean型
	 *
	 * 2、元素标签可用attr
	 * 	jq_verify 有此属性则表示校验空
	 * 	jq_verify="xxxx" xxx一般为提示内容，如果xxx为regix_mess里的key则会进行对应的校验，例如xxx为idCard则会校验是否是身份证格式
	 * 	jq_verify_minNum="5" 则表示不能少于5位
	 * 	jq_verify_maxNum="7" 则表示不能多于7位
	 *
	 * 3、如果外部要自定义判断正则可以如下操作
	 * 	例如：要加一个判断isTest
	 * 
	 * 	TB_JUDGE.regix_mess["isTest"] = {"mess":"提示语","reg":正则表达式};
	 */
	verify : function($obj,params){
		
		if(params == undefined){
			params = {};
		}
		
		var callBack = params.callBack;
		var verifyValue = params.verifyValue;
		
		var objs = $obj.find("[jq_verify]");
		if(verifyValue){
			objs = $obj.find("[jq_verify="+verifyValue+"]");
		}
		var res = true;
		for(var index=0; index<objs.length; index++){
			
			var obj = objs.eq(index);
			
			var verify = obj.attr("jq_verify");
			var verifyMinNum = obj.attr("jq_verify_minNum");
			var verifyMaxNum = obj.attr("jq_verify_maxNum");

			var value = obj.val();
			
			if(this.regix_mess[verify] != null && this.regix_mess[verify] != undefined){
				
				var reg = this.regix_mess[verify].reg;
				var mess = this.regix_mess[verify].mess;
				
	 			if(!reg.test(value)){
	 				if(callBack){
	 					callBack(obj,mess);
	 				}
	 				res = false;
	 				obj.focus();
	 				return;
	 			}
				
			}else{
				
				var tagName = obj[0].tagName;
				if(!this.notNull(value)){
	 				res = false;
	 				var content = verify+"不能为空";
	 				if(tagName == "SELECT"){
	 					content = "请选择"+verify;
	 				}
	 				if(callBack){
	 					callBack(obj,content);
	 				}
	 				obj.focus();
	 				return;
	 			}
				
			}
			//有字数控制
			if(verifyMinNum && value.length < verifyMinNum){
					
				res = false;
				if(callBack){
					callBack(obj,verify+"至少"+verifyMinNum+"位");
				}
				obj.focus();
				return;
				
			}
			if(verifyMaxNum && value.length > verifyMaxNum){
				
				res = false;
				if(callBack){
					callBack(obj,verify+"最多"+verifyMaxNum+"位");
				}
				obj.focus();
				return;
				
			}
			
		}
		return res;
	},
	/**
	 * 传入多个数组
	 */
	verifies : function($objs,params){
		
		var res = true;
		
		for(var index in $objs){
			
			var $obj = $objs[index];
			res = TB_JUDGE.verify($obj,params);
			if(!res){
				return;
			}
		}
		
		return res;
	}
}

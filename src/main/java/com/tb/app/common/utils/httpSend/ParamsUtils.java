package com.tb.app.common.utils.httpSend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
/**
 * 参数处理
 * @author Think
 *
 */
public class ParamsUtils {

	/**
	 * 将map类型参数转换为key=value&key=value的形式
	 * @param params
	 * @return
	 */
	public static String transParams(Map<String,String> params) {
		
		List<String> keys = new ArrayList<String>();
		
		Set<String> keySet = params.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		
		while(keyIterator.hasNext()) {
			
			String innerKey = keyIterator.next();
			keys.add(innerKey);
			
		}
		String res = "";
		for(int index=0; index<keys.size(); index++) {
			
			String innerKey = keys.get(index);
			String value = (String) params.get(innerKey);
			
			if(StringUtils.isNotBlank(value)) {//值不为空的参与签名
				
				if(index == keys.size()-1) {//最后一个
					
					res += innerKey+"="+value;
					
				}else {
					
					res += innerKey+"="+value+"&";
					
				}
				
			}
			
		}
		
		return res;
	}
	/***
	 * 将req请求参数封装为map形式
	 * @param req
	 * @return
	 */
	public static Map<String,String> transFromReq(HttpServletRequest req){
		
		Map<String, String[]> parameters=req.getParameterMap();
		Map<String,String> params = Maps.newHashMap();
		Iterator<String> iter=parameters.keySet().iterator();
		while(iter.hasNext()){
			String key=(String) iter.next();
			String value=req.getParameter(key);

			params.put(key, value);
			
		}
		
		return params;
	}
}

package com.tb.app.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 一些不知道怎么分类的工具方法
 */
public class CommonUtils {

    /**
     * 根据对象中的某个字段进行去重
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

	/**
	 * 获取随机字符串
	 * @param num 该随机字符串的长度
	 * @param type 字符串类型 
	 * 	0：纯数字
	 *  1：字母+数字
	 * @return
	 */
	public static String randomStr(int num,int type) {
		int index = 0;
		String res = "";
		while(index < num) {
			switch(type) {
				case 0://纯数字
					res +=Math.round(1 + Math.random()*8);
					break;
				case 1://字母+数字
					long judge =Math.round(Math.random()*8);
					if(judge <=2 ) {//数字
						res +=Math.round(1 + Math.random()*8);
					}else if(judge > 2 && judge <= 5) {//小写字母
						res +=(char)Math.round(97 + Math.random()*25);
					}else{//大写字母
						res +=(char)Math.round(65 + Math.random()*25);
					}
					break;
				case 2:
					break;
				case 3:
					break;
				default:
					
			}
			index ++;
		}
		
		return res;
	}

}

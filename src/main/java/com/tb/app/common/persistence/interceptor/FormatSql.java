package com.tb.app.common.persistence.interceptor;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;

/**
 * 格式化sql语句
 * @author tb
 *
 */
public class FormatSql {
	
	/**
	 * 取查询的sql信息
	 * 
	 * @param sourceSql sql语句
	 * @return
	 */
	public static String replaceSelectColumn2Count(String sourceSql) {
		
		
		String delSql = sourceSql;
		delSql = delSql.toLowerCase();
		delSql = delSql.replaceFirst("select", "");
		
		//第一个from的索引
		int fromIndex = getCloumnOverFrom(6,delSql);
		//select—>from
		String sCloumnsF = sourceSql.substring(0,fromIndex);
		//cloumns
		String cloumns = sourceSql.substring(6,fromIndex);
		//如果cloumns有distinct的话用原columns
		if (cloumns.contains("?") || cloumns.toLowerCase().contains("distinct")) {
			//sCloumnsF = sCloumnsF.replace(cloumns, " 1,"+cloumns+" ");
		}else sCloumnsF =	sCloumnsF.replace(cloumns, " 1 ");
		
		//重组后的sql
		sourceSql = sCloumnsF + sourceSql.substring(fromIndex);
		
		sourceSql = "select count(1) from (" + sourceSql + ") tmp_count";

		
		return sourceSql;

	}
	
	/**
	 * 取查询的sql信息
	 * 
	 * 只提取了列和表名=-=
	 * @param sourceSql sql语句
	 * @return
	 */
	public static Map<String, String> getSelectSqlInfo(String sourceSql) {
		
		Map<String, String> sqlInfo = Maps.newHashMap();
		
		String delSql = sourceSql;
		delSql = delSql.toLowerCase();
		delSql = delSql.replaceFirst("select", "");
		
		int fromIndex = getCloumnOverFrom(6,delSql);
		
		String cloumns = sourceSql.substring(6,fromIndex);
		String table = getTable(fromIndex, sourceSql);
		int tableIndex = sourceSql.substring(fromIndex).indexOf(table);
		
		sqlInfo.put("cloumns", cloumns);
		sqlInfo.put("table", table);
		
		return sqlInfo;
	}
	/**
	 * 获取列结束的from的index
	 * @param removeNumber 去掉的字符数
	 * @param param sql语句
	 * @return
	 */
	private static int getCloumnOverFrom(int removeNumber,String param) {
		
		int selectIndex = param.indexOf("select");
		int fromIndex = param.indexOf("from");
		if (selectIndex > 0 && fromIndex > selectIndex) {//在最外层的select和from之间存在子的sf，去掉该sf
			
			param = param.replaceFirst("select", "");
			param = param.replaceFirst("from", "");
			removeNumber = removeNumber+6+4;
			return getCloumnOverFrom(removeNumber,param);
			
		}else {
			 return param.indexOf("from")+removeNumber;
		}
	
	}
	/**
	 * 获取表名
	 * @param overFromIndex 列结束的from的index
	 * @param param sql
	 * @return
	 */
	private static String getTable(Integer overFromIndex,String param) {
		
		Pattern pattern = Pattern.compile("^from\\s{1,}\\S{1,}");
		Matcher matcher = pattern.matcher(param.substring(overFromIndex));
		String str1 = "";
		if (matcher.find()) {
			str1 = matcher.group();
			str1 = str1.substring(4);
			
			pattern = Pattern.compile("\\S{1,}");
			matcher = pattern.matcher(str1);
			if (matcher.find()) {
				str1 = matcher.group();
			}
		}
		return str1;
	}
	
	public static void main(String[] args) {
		
		System.out.println(replaceSelectColumn2Count("select a,b,(select c,d,(select * from m where a in (select id from c where a = ?)) as e from a) as f,(select m from a) from  table where a in (select id from c where a = (select d from f))"));
		
	}
}

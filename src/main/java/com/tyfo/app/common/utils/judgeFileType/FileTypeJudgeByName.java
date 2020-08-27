package com.tyfo.app.common.utils.judgeFileType;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

public class FileTypeJudgeByName {

	/**
	 * 获取文件类型
	 * @param name
	 * @return type 1 表示图片,2 表示文档,3 表示视频,4 表示种子,5 表示音乐,7 表示其它
	 */
	public static Integer getType(String name) {
		
		Integer type = 7;
		
		if(isImage(name)) {
			
			type = 1;
			
		}else if(isVideo(name)) {
			
			type = 3;
			
		}else if(isDocument(name)) {
			
			type = 2;
			
		}
		
		return type;
	}
	/**
	 * 是否是图片
	 * @param name
	 * @return
	 */
	public static boolean isImage(String name) {
		
		List<String> par = Lists.newArrayList();
		par.add("jpg");
		par.add("gif");
		par.add("png");
		par.add("jpeg");
		
		return getType(par,name);
		
	}
	
	/**
	 * 是否是视频
	 * @param name
	 * @return
	 */
	public static boolean isVideo(String name) {
		
		List<String> par = Lists.newArrayList();
		par.add("mp4");
		par.add("avi");
		par.add("flv");
		par.add("ogg");
		par.add("3gp");
		par.add("vob");
		par.add("webm");
		par.add("wmv");
		
		return getType(par,name);
		
	}
	
	/**
	 * 是否是文档
	 * @param name
	 * @return
	 */
	public static boolean isDocument(String name) {
		
		List<String> par = Lists.newArrayList();
		par.add("xls");
		par.add("xlsx");
		par.add("doc");
		par.add("docx");
		par.add("txt");
		
		return getType(par,name);
		
	}
	/**
	 * 是否是Excel
	 * @param name
	 * @return
	 */
	public static boolean isExcel(String name) {
		
		List<String> par = Lists.newArrayList();
		par.add("xls");
		par.add("xlsx");
		
		return getType(par,name);
		
	}
	
	/**
	 * 是否是文档
	 * @param name
	 * @return
	 */
	public static boolean isZip(String name) {
		
		List<String> par = Lists.newArrayList();
		par.add("zip");
		par.add("rar");
		
		return getType(par,name);
		
	}
	/**
	 * 根据文件后缀判断
	 * @param name 包含后缀的文件名或是文件路径
	 * @param par 对应类型文件的后缀列表
	 * @return
	 */
	public static boolean getType(List<String> par,String name) {
		
		if(StringUtils.isBlank(name)) {
			
			return false;
			
		}
		name = name.toLowerCase();
		
		String extName = name.substring(name.lastIndexOf(".")+1);
		if(par.contains(extName)) {
			return true;
		}
		
		return false;
	}
	
	
}

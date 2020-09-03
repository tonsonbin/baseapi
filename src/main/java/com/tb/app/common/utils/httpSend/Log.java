package com.tb.app.common.utils.httpSend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class Log {

	private static final String SPLIT_TYPE1 = "=====================================================";
	
	public static String Err = "errLog";
	public static String INFO = "infoLog";
	
	private String mess="";
	private String desc;
	
	public Log(String desc) {
		this.desc = desc;
	}
	public void append(String message) {
		this.mess +=this.desc+"："+message+"\r,";
	};
	public String get() {
		String tempMess = SPLIT_TYPE1+this.desc+" 开始"+SPLIT_TYPE1+"\r,";
		tempMess +=this.mess;
		tempMess +=SPLIT_TYPE1+this.desc+" 结束"+SPLIT_TYPE1+"\r,";
		return tempMess;
	}

	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {  
        String ip = request.getHeader("X-Forwarded-For");
        if(ip!=null && !ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)){
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(ip!=null && !ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr(); 
	}
	
	/**
	 * web项目的Log 注：由于要获取路径，必须要先定义request
	 * 
	 * @param type
	 *            该日志类型
	 * @param mess
	 *            信息
	 */
	public static void writeLogForWeb(String type, String mess,HttpServletRequest request) {
		if (request == null) {
			System.out.println(Log.getInfo("request is't defined"));
			return;
		}
		FormatDate fd = new FormatDate();
		// 获取当前工程的路径
		String projectPath = request.getSession().getServletContext()
				.getRealPath("/");// // 例如当前为F:\特殊demo\servlet\Tools
		//System.out.println(projectPath);
		// 指定错误输出文件
		try {
			String fileDirPath = projectPath + "/weblog/" + type + "/";
			File fileDir = new File(fileDirPath);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File errFile = new File(fileDirPath + fd.year + "_" + fd.month
					+ "_" + fd.day + ".txt");
			if (!errFile.exists()) {
				errFile.createNewFile();
			}
			FileWriter fw = new FileWriter(errFile, true);
			fw.write(fd.hours + ":" + fd.minutes + ":" + fd.seconds + "-----"
					+ mess);
			fw.write("\r,");
			fw.flush();
			fw.close();
		} catch (Exception e) {
			System.out.println(Log.getInfo(e));
		}

	}


	/**
	 * 常规java工程日志
	 * 
	 * @param type
	 * @param mess
	 */
	public static void writeLogForNormal(String type, String mess) {
		System.out.println(mess);
		String projectPath = System.getProperty("user.dir");
		FormatDate fd = new FormatDate();
		try {
			File fileDir = new File(projectPath + "/nonpurchases/normallog/" + type + "/");
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			File file = new File(fileDir, fd.year + "_" + fd.month + "_"
					+ fd.day + ".txt");
			//System.out.println(getInfo("日志存放地址"+file.getAbsolutePath()));
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file, true);
			fw.write(fd.hours + ":" + fd.minutes + ":" + fd.seconds + "-----"
					+ mess);
			fw.write("\r,");
			fw.flush();
			fw.close();
		} catch (FileNotFoundException e) {
			System.out.println(Log.getInfo(e));
		} catch (SecurityException e) {
			System.out.println(Log.getInfo(e));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Log.getInfo(e));
		}
	}
	/**
	 * 普通的信息记录
	 * 获取写该日志代码的类、方法及行号
	 * @param addInfo
	 * @return
	 */
	public static String getInfo(String addInfo){
		StackTraceElement[] trace = Thread.currentThread().getStackTrace(); // ע��!�������±�Ϊ2��,������Ϊ1��
		StackTraceElement tmp = trace[2]; 
		String info=tmp.getClassName() + "." + tmp.getMethodName() + "(" + tmp.getFileName() + ":" + tmp.getLineNumber() + "):"+addInfo ;
		String className=tmp.getClassName();
		String methodName=tmp.getMethodName();
		String fileName=tmp.getFileName();
		int lineNumber=tmp.getLineNumber();
		return new FormatDate().normalDate()+" "+info;
	}
	/**
	 * 错误记录
	 * 获取写该日志代码的类、方法及行号
	 * @param e
	 * @return
	 */
	public static String getInfo(Exception e){
		StackTraceElement[] trace = Thread.currentThread().getStackTrace(); // ע��!�������±�Ϊ2��,������Ϊ1��
		StackTraceElement tmp = trace[2]; 
		String info=tmp.getClassName() + "." + tmp.getMethodName() + "(" + tmp.getFileName() + ":" + tmp.getLineNumber() + "):"+e.getMessage() ;
		String className=tmp.getClassName();
		String methodName=tmp.getMethodName();
		String fileName=tmp.getFileName();
		int lineNumber=tmp.getLineNumber();
		return info;
	}
	public static void main(String[] valuew) {
		try {
			int res = 1 / 0;
		} catch (Exception e) {
			writeLogForNormal(Log.Err,Log.getInfo(e));
		}
	}
}

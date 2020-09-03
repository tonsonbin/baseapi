package com.tb.app.common.utils.aliOSS;

import java.io.File;

import com.aliyun.oss.OSSClient;
import com.tb.app.common.security.IdGen;

public class ALYOssFileUpload extends Config{
	
	/**
	 * 阿里云oss上传文件
	 * @param objectName 对象名称 即/对象路径
	 * @param file 文件
	 * @return
	 */
	public static boolean upload(String objectName,File file) {
		
		ALYOss alyOss = ALYOss.init().open();
		if(alyOss == null) {
			
			return false;
			
		}
		
		
		OSSClient ossClient = alyOss.getOssClient();
	
		try {
		// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
		ossClient.putObject(bucketName, objectName, file);
		
		}catch(Exception e) {
			
			e.printStackTrace();
			return false;
			
		}finally {
			
			alyOss.close();
			
		}
			
		return true;
	}
	
	public static void main(String[] args) {
		
		System.out.println(upload("files/honor/videos/"+IdGen.uuid(),new File("F:\\video\\1.mp4")));
		
	}
}

package com.tb.app.common.utils.aliOSS;

import java.net.URL;
import java.util.Date;

import com.aliyun.oss.OSSClient;

public class ALYOssGetPath extends Config{

	/**
	 * 根据objectName获取播放地址
	 * @param objectName
	 * @return
	 */
	public static String getPlayPath(String objectName) {
		
		ALYOss alyOss = ALYOss.init().open();
		if(alyOss == null) {
			
			return null;
			
		}
		
		
		OSSClient ossClient = alyOss.getOssClient();
			
		try {
		    //过期时间
		    Date expiration = new Date(new Date().getTime() + 3600 * 1000);
		    //加密
		    URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
		    
			return url.toString();
		}catch(Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(getPlayPath("files/sysArea/image//6e6d32dcb23c42858f9a0415ef439669.png"));
		
	}
}

package com.tyfo.app.common.utils.aliOSS;

import com.aliyun.oss.OSSClient;

class ALYOss extends Config{

	private OSSClient ossClient;
	
	public static ALYOss init() {
		
		return new ALYOss();
	}
	
	/**
	 * 打开OSSClient
	 * @return
	 */
	public ALYOss open() {
		
		try {
			// 创建OSSClient实例。
			ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			
			return this;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
		
	}
	/**
	 * 关闭
	 */
	public void close() {
		
		try {
			
			ossClient.shutdown();
			
		}catch(Exception e) {
			
			
			
		}
		
	}

	public OSSClient getOssClient() {
		return ossClient;
	}

	public void setOssClient(OSSClient ossClient) {
		this.ossClient = ossClient;
	}
	
	
}

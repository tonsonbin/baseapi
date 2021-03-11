package com.tb.app.model.weixin.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.coolsn.common.weixin.dao.WXDao;
import com.tb.app.common.YamlConfigWeixin;

import net.sf.json.JSONObject;

/**
 * 
 * 微信二维码
 * 
 * 1、scene 最大长度为32位
 * 2、如果小程序没有正式上线，传了page的话就会获取不到二维码
 * 
 * @author tangbin
 * @date 2021年3月11日
 */
public class WXQRCode {

	//自动线条
	private final static boolean autoColor = true;
	//是否需要透明底色，为 true 时，生成透明底色的小程序返回值
	private final static boolean isHyaline = false;
	
	/**
	 * 
	 * 取小程序二维码流
	 * 
	 * 1、无限制二维码（不限制总数量）
	 * 2、使用系统自动生成的accessToken
	 * 
	 * @param scene 二维码数据
	 * @return InputStream 返回null表示失败
	 */
	public static InputStream sqrcodeInputStreamUnLimit(String scene) {
		
		return sqrcodeInputStreamUnLimit(scene, null);
		
	}
	
	/**
	 * 
	 * 取小程序二维码流
	 * 
	 * 1、无限制二维码（不限制总数量）
	 * 2、使用自己的accessToken
	 * 
	 * @param scene 二维码数据
	 * @param accessToken 微信全局accessToken
	 * @return InputStream 返回null表示失败
	 */
	public static InputStream sqrcodeInputStreamUnLimit(String scene,String accessToken) {
		
		WXDao wxDao = new WXDao().init(YamlConfigWeixin.getAppId(), YamlConfigWeixin.getAppSecret());
		return wxDao.getWXSPQRCodeInputStreamUnLimit(Integer.valueOf(YamlConfigWeixin.getSqrcodeWidth()), autoColor, new JSONObject()
				, isHyaline, YamlConfigWeixin.getSqrcodePage(), scene);
		
	}
	
	/**
	 * 
	 * 取小程序二维码流
	 * 
	 * 1、有限制限制二维码（会限制总数量）
	 * 2、使用自己的accessToken
	 * 
	 * @param scene 二维码数据
	 * @param accessToken 微信全局accessToken
	 * @return InputStream 返回null表示失败
	 */
	public static InputStream sqrcodeInputStream(String scene) {
		
		return sqrcodeInputStream(scene, null);
		
	}
	
	/**
	 * 
	 * 取小程序二维码流
	 * 
	 * 有限制限制二维码（会限制总数量）
	 * 
	 * @param scene 二维码数据
	 * @return InputStream 返回null表示失败
	 */
	public static InputStream sqrcodeInputStream(String scene,String accessToken) {
		
		WXDao wxDao = new WXDao().init(YamlConfigWeixin.getAppId(), YamlConfigWeixin.getAppSecret());
		return wxDao.getWXSPQRCodeInputStream(Integer.valueOf(YamlConfigWeixin.getSqrcodeWidth()), autoColor, new JSONObject()
				, isHyaline, YamlConfigWeixin.getSqrcodePage());
		
	}
	
	/**
	 * 将二维码流数据输出到输出流中
	 * @param inputStream
	 * @param os
	 */
	public static void transToOS(InputStream inputStream,OutputStream os) {
		
		BufferedImage image;
		try {
			image = ImageIO.read(inputStream);
			ImageIO.write(image, "jpeg", os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

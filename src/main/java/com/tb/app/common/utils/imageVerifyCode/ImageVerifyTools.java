package com.tb.app.common.utils.imageVerifyCode;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.configurer.cachemanager.CacheBase;
import com.tb.app.configurer.cachemanager.CacheConstant;
import com.tb.app.configurer.cachemanager.CacheFactory;
import com.tb.app.configurer.cachemanager.EhCache;

import net.sf.ehcache.Cache;

public class ImageVerifyTools {
	
	private static String IMAGE_CACHE_KEY_PRE = "imageCode_";
	
	/**
	 * 获取图形验证码
	 * @param verifyKey 校验key
	 * @param request
	 * @param response
	 * @throws IOException
	 */
    public static void get(String verifyKey,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String code = ValidateCodeServlet.createCode();
    	if (StringUtils.isBlank(verifyKey)) {
			code = "";
		}
		CacheFactory.getCache().put(IMAGE_CACHE_KEY_PRE+verifyKey, code);
    	new ValidateCodeServlet().createImage(code,request, response);
    }
    
    /**
	 * 获取图形验证码-base64
	 * @param verifyKey 校验key
	 * @param request
	 * @param response
	 * @throws IOException
	 */
    public static Result getBase64(String verifyKey,String width,String height) throws IOException {
    	
    	String code = ValidateCodeServlet.createCode();
    	if (StringUtils.isBlank(verifyKey)) {
			code = "";
		}
    	CacheFactory.getCache().put(IMAGE_CACHE_KEY_PRE+verifyKey, code);
    	String base64 = new ValidateCodeServlet().createImageBase64(code,width, height);
    	
    	return ResultGenerator.genSuccessResult(base64);
    }
    
    
	/**
	 * 校验
	 * @param verifyKey 校验key
	 * @param code 验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
    public static Result verify(String verifyKey,String code) throws IOException {
    	
    	return verify(false,verifyKey, code);
    }
    
    /**
	 * 校验
	 * @param reset 是否进行重置，一般在业务处理进行校验的时候会进行重置
	 * @param verifyKey 校验key
	 * @param code 验证码
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
    public static Result verify(Boolean reset,String verifyKey,String code) throws IOException {
    	
    	//之前的验证码
    	String oldCode = (String) CacheFactory.getCache().get(IMAGE_CACHE_KEY_PRE+verifyKey);
    	
    	if (reset) {
			//对验证码进行重置
    		CacheFactory.getCache().remove(IMAGE_CACHE_KEY_PRE+verifyKey);
    		
		}
    	
    	//校验是否正确
    	if (!ValidateCodeServlet.validate(oldCode,code )) {
			return ResultGenerator.genFailResult("图形验证码错误！");
		}
    	
    	return ResultGenerator.genSuccessResult();
    }
    
}

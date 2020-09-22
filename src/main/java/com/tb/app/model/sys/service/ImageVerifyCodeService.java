package com.tb.app.model.sys.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.service.CrudService;
import com.tb.app.common.utils.EhCacheUtil;
import com.tb.app.common.utils.imageVerifyCode.ValidateCodeServlet;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.entity.App;
import com.tb.app.model.sys.entity.SysUrl;
import com.tb.app.model.sys.mapper.AppMapper;
import com.tb.app.model.sys.mapper.SysUrlMapper;


/**
 * 图形验证码
 */
@Service
@Transactional(readOnly = true)
public class ImageVerifyCodeService extends CrudService<AppMapper, App> {

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
    	EhCacheUtil.put(EhCacheUtil.COMMON_CACHE, IMAGE_CACHE_KEY_PRE+verifyKey, code);
    	new ValidateCodeServlet().createImage(code,request, response);
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
    	String oldCode = (String) EhCacheUtil.get(EhCacheUtil.COMMON_CACHE, IMAGE_CACHE_KEY_PRE+verifyKey);
    	
    	if (reset) {
			//对验证码进行重置
    		EhCacheUtil.remove(EhCacheUtil.COMMON_CACHE, IMAGE_CACHE_KEY_PRE+verifyKey);
    		
		}
    	
    	//校验是否正确
    	if (!ValidateCodeServlet.validate(oldCode,code )) {
			return ResultGenerator.genFailResult("图形验证码错误！");
		}
    	
    	return ResultGenerator.genSuccessResult();
    }
}

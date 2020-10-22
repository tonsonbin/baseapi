package com.tb.app.model.sys.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.utils.imageVerifyCode.ImageVerifyTools;
import com.tb.app.common.web.BaseController;
import com.tb.app.common.web.Result;


/**
 * 
 * 图形验证码
 * 
 * @author Think
 *
 */
@RequestMapping("${apiPath}/sys/imageVerifyCode")
@RestController
public class ImageVerifyCodeController extends BaseController{

	/**
	 * 获取图形验证码-直接返回图片
	 * @param verifyKey 校验key
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/unauth/get")
    public void get(String verifyKey,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	ImageVerifyTools.get(verifyKey, request, response);
    }
    
	/**
	 * 获取图形验证码-返回base64格式
	 * @param verifyKey 校验key
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/unauth/get")
    public void getBase64(String verifyKey,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
		String width = request.getParameter("width");
		String height = request.getParameter("height");
		
		ImageVerifyTools.getBase64(verifyKey, width, height);
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
	@RequestMapping("/unauth/verify")
    public Result verify(String verifyKey,String code,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	return ImageVerifyTools.verify(verifyKey, code);
    }

}

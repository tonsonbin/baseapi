package com.tb.app.model.sys.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.utils.EhCacheUtil;
import com.tb.app.common.utils.imageVerifyCode.ValidateCodeServlet;
import com.tb.app.common.web.BaseController;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.sys.service.ImageVerifyCodeService;


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
	 * 获取图形验证码
	 * @param verifyKey 校验key
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/unauth/get")
    public void get(String verifyKey,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	ImageVerifyCodeService.get(verifyKey, request, response);
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
    	
    	return ImageVerifyCodeService.verify(verifyKey, code);
    }

}

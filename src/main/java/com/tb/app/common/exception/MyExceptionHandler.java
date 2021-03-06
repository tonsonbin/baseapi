package com.tb.app.common.exception;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.app.common.YamlConfig;
import com.tb.app.common.interceptor.AllInterceptor;
import com.tb.app.common.utils.Constant;
import com.tb.app.common.utils.aesc.AesCbcUtil;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultCode;
import com.tb.app.model.sys.entity.RequestLog;
import com.tb.app.model.sys.utils.runner.LogRunnerFactory;


@ControllerAdvice
public class MyExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception e) throws IOException {

    	
      //在视图渲染之前执行，可以设置modelAndView
    	String baseUrl=request.getContextPath();
    	logger.info("visit "+baseUrl+" throw exception "+e.getLocalizedMessage());        
      //将错误堆栈打印处理便于调试
    	e.printStackTrace();
        
    	//请求连接
        String servletPath = request.getServletPath();
    	
    	//设置api错误处理
        Result result = new Result();
        
        //设置视图错误处理
        ModelAndView modelAndView = new ModelAndView();
        String viewName = "/common/error/error";
        
        //是否是返回视图，默认返回视图，根据项目的实际情况自己做设置，如果改为false则默认返回api处理
        boolean viewR = true;
        //判断请求类型
        if (servletPath != null && servletPath.startsWith(YamlConfig.getApiPath())) {

        	viewR = false;
            
		}
        
        if (e instanceof ServiceException) {
            
        	//serviceException 动态code
        	String code = ((ServiceException) e).getCode();
        	if (StringUtils.isNoneBlank(code)) {

    	        result.setCode(code).setMessage(e.getLocalizedMessage());
    	        
			}else {
				
    	        result.setCode(ResultCode.FAIL).setMessage(e.getLocalizedMessage());
				
			}
            logger.info(e.getMessage());
        	
        }else if (e instanceof ServletException) {
        	
        	//ServletException
            result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
            
        }else if (e instanceof BindException) {
        	
        	//参数类型错误
            List<ObjectError> errors = ((BindException) e).getAllErrors();
            if (errors != null && errors.size() > 0) {
                result.setCode(ResultCode.FAIL).setMessage(errors.get(0).getDefaultMessage());
            } else {
                result.setCode(ResultCode.FAIL).setMessage("参数错误");
            }
            
        } else if (e instanceof MyJspException) {
        	
        	result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage(e.getLocalizedMessage());
            
        } else {
        	
        	//未知异常
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("系统异常，请联系管理员");
            String message;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s",
                        request.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        e.getMessage());
            } else {
                message = e.getMessage();
            }
            logger.error(message, e);
            
        }
           
        //需要登录
        if (StringUtils.equals(result.getCode(),String.valueOf(ResultCode.LOGIN_GETUSERINFO.code()))) {
        	viewName = "redirect:/view/sys/unauth/login";
		}
        
        //吐出真实提示信息
		//真实的错误信息
		String relMessage = e.getLocalizedMessage();
		//该线程请求本机ip
		String localIp = request.getLocalAddr()+":"+request.getLocalPort();
        String ISI = "";
    	try {
    		ISI = AesCbcUtil.encryptMode1(localIp+"==>"+relMessage, Constant.SERVERINFO_E_KEY);
    	} catch (Exception e1) {
    		// TODO Auto-generated catch block
    	}

        //日志入库处理
        RequestLog requestLog = AllInterceptor.requestInfoThreadLocal.get();
        if (requestLog == null) {
        	
			requestLog = new RequestLog();
			
		}

        requestLog.setSave(true);
    	requestLog.setFinallyOut(true);
    	requestLog.setResponseJson(result==null?"":result.toString());
        requestLog.setException(e.getLocalizedMessage());
        LogRunnerFactory.runResultLog(requestLog);
        
        //判断请求类型
        if (viewR) {

        	//视图请求
			modelAndView.addObject("code", result.getCode());
			modelAndView.addObject("message", result.getMessage());
			modelAndView.addObject("isi", ISI);
            modelAndView.setViewName(viewName);
            
		}else {
			
			//api接口请求
            return result.setIsi(ISI);
			//return result;
		}
        
        return modelAndView;
       
    }
    
    /**
     * 对于NoHandlerFoundException的处理
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public Object noHandlerFoundException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception e) throws IOException {

    	
      //在视图渲染之前执行，可以设置modelAndView
    	String baseUrl=request.getContextPath();
    	logger.info("visit "+baseUrl+" throw exception "+e.getLocalizedMessage());        
      //将错误堆栈打印处理便于调试
    	e.printStackTrace();
        
    	//请求连接
        String servletPath = request.getServletPath();
        
        //设置视图错误处理
        ModelAndView modelAndView = new ModelAndView();
        
        //是否是返回视图，默认返回视图，根据项目的实际情况自己做设置，如果改为false则默认返回api处理
        boolean viewR = true;
        //判断请求类型
        if (servletPath != null && servletPath.startsWith(YamlConfig.getApiPath())) {

        	viewR = false;
            
		}

        //吐出真实提示信息
		//真实的错误信息
		String relMessage = e.getLocalizedMessage();
		//该线程请求本机ip
		String localIp = request.getLocalAddr()+":"+request.getLocalPort();
        String ISI = "";
    	try {
    		ISI = AesCbcUtil.encryptMode1(localIp+"==>"+relMessage, Constant.SERVERINFO_E_KEY);
    	} catch (Exception e1) {
    		// TODO Auto-generated catch block
    	}
        
        //判断请求类型
        if (viewR) {

        	//视图请求
			modelAndView.addObject("message", "页面不存在");
			modelAndView.addObject("isi", ISI);
            
		}else {
			
			//api接口请求
			Result result = new Result().setCode("404").setMessage("接口不存在").setIsi(ISI);
			return result;
		}
        
        return modelAndView;
       
    }
    
    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}

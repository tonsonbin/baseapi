package com.tyfo.app.common.exception;

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
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyfo.app.common.YamlConfig;
import com.tyfo.app.common.interceptor.AllUrlInterceptor;
import com.tyfo.app.common.web.Result;
import com.tyfo.app.common.web.ResultCode;
import com.tyfo.app.model.sys.entity.RequestLog;
import com.tyfo.app.model.sys.utils.runner.LogRunnerFactory;


@ControllerAdvice
public class MyExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
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
        String viewName = "/error/error";
        
        //是否是返回视图，默认返回视图
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
        	
        } else if (e instanceof NoHandlerFoundException) {
        	
            result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
            //设置视图页面
            viewName = "/error/404";
            
        } else if (e instanceof ServletException) {
        	
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
            //设置视图页面
            viewName = "/error/500";
            
        } else {
        	
        	//未知异常
            result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMessage("接口 [" + request.getRequestURI() + "]" +
                    " 内部错误，请联系管理员");
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
        
        //判断请求类型
        if (viewR) {

        	//视图请求
			modelAndView.addObject("message", result.getMessage());
            modelAndView.setViewName(viewName);
            
		}else {
			
			//api接口请求
            responseResult(response, result);
			
		}
        
        RequestLog requestLog = AllUrlInterceptor.requestInfoThreadLocal.get();
        if (requestLog != null) {
        	requestLog.setSave(true);
            requestLog.setResponseJson(result.toString());
            LogRunnerFactory.runResultLog(requestLog);
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

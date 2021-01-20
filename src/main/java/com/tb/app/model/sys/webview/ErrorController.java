package com.tb.app.model.sys.webview;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.tb.app.common.utils.Constant;
import com.tb.app.common.utils.aesc.AesCbcUtil;

@Controller
public class ErrorController extends BasicErrorController {

	public ErrorController(){
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    private static final String PATH = "/common/error/404";


	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		
		//吐出真实提示信息
		//该线程请求本机ip
		String localIp = request.getLocalAddr()+":"+request.getLocalPort();
		String ISI = "";
		try {
			ISI = AesCbcUtil.encryptMode1(localIp, Constant.SERVERINFO_E_KEY);
			model.put("isi", ISI);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
		
		return modelAndView == null ? new ModelAndView(getErrorPath(),model) : modelAndView;
	}

	@Override
    public String getErrorPath() {
        return PATH;
    }
}

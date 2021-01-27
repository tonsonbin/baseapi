package com.tb.app.model.sys.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolsn.modules.tb.judgeParams.JudgeParams;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.security.IdGen;
import com.tb.app.common.utils.CommonUtils;
import com.tb.app.common.utils.Constant;
import com.tb.app.common.utils.EhCacheUtil;
import com.tb.app.common.utils.FormatDate;
import com.tb.app.common.utils.imageVerifyCode.ValidateCodeServlet;
import com.tb.app.model.sys.entity.SmsCode;
import com.tb.app.model.sys.mapper.SmsMapper;
import com.tb.app.model.sys.utils.SmsUtil;



/**
 * Created by CodeGenerator on 2020/06/10.
 * 手机验证码
 * 
 */
@Service
@Transactional(readOnly = true)
public class SmsService{
	
	@Resource
	SmsMapper smsMapper;
	
	/**
	 * 发送验证码
	 * @param mobile 手机号
	 * @param type 发送类型
			0 添加绑定手机号 E平台占用
			1 修改支付密码 E平台占用
			NC01 本系统登录验证码
			NC02 本系统下单验证码
	 * @param imageCode 
	 * @param verifyKey 
	 */
	@Transactional(readOnly = false)
	public void sendCode(String mobile,String type, String imageCode, String verifyKey)
	{
		
		if(StringUtils.isBlank(mobile))
		{
			throw new ServiceException("手机号不能为空");
		}
		if(StringUtils.isBlank(type))
		{
			throw new ServiceException("type（发送类型）不能为空");
		}
		
		//校验手机号
		if (!JudgeParams.isPhoneNum(mobile)) {
			throw new ServiceException("手机号格式错误！");
		}
		
		//校验图形验证码
		String oldCode = (String) EhCacheUtil.get(EhCacheUtil.COMMON_CACHE, "imageCode_"+verifyKey);
		//重置
		EhCacheUtil.remove(EhCacheUtil.COMMON_CACHE, "imageCode_"+verifyKey);
		if (!ValidateCodeServlet.validate(oldCode,imageCode )) {
			throw new ServiceException("图形验证码错误");
		}
				
		
		SmsCode smsCode = new SmsCode();
		smsCode.setMobile(mobile);
		smsCode.setType(type);
		SmsCode code = smsMapper.getSmsCode(smsCode);
		
		
		Boolean isSend = false;//默认不发短信
		
		if(code == null) 
		{
			isSend = true;
		}	
		else 
		{
			long times 		  = code.getActualTime().getTime();//短信成功发送毫秒数
			long currentTimes = System.currentTimeMillis();//当前系统时间的毫秒数
			
			if(currentTimes <= times)
			{
				throw new ServiceException("短信已发送，请稍后再试");
			}
			else 
			{
				isSend = true;
			}
		}
		
		if(isSend) 
		{
			//生成6位随机数验证码
			String randomCode = CommonUtils.randomStr(6, 0);
			
			String content = "";
			if (StringUtils.equals(type,Constant.SMS_TYPE_BASE)) {
				content = "您本次验证码："+randomCode+"，您正在登录平台，有效期5分钟，切勿将验证码泄露于他人。";
			}else{
				throw new ServiceException("错误类型码！");
			}
			
			
			//调用网关接口发送短信
			boolean sendOk = true;//SmsUtil.sendSms(mobile,content);
			if(!sendOk)
			{
				throw new ServiceException("发送异常");
			}
			
			smsCode.setCode(randomCode);
			smsCode.setCreateTime(new Date());
			smsCode.setActualTime(FormatDate.getInstance().setDisSecond(SmsUtil.SEND_CODE_WAIT_SECOND).getCalendar().getTime());
			smsCode.preInsert();
			smsCode.setId(IdGen.uuid());
			smsMapper.insert(smsCode);
		}
		
	}
	
	/**
	 *手机号和验证码验证
	 * @param mobile 手机号
	 * @param code 验证码
	 */
	 @Transactional(readOnly = false)
	public void verificationCode(String mobile, String code,String type) 
	{

		 //参数校验
		 if (StringUtils.isBlank(mobile)) {
			throw new ServiceException("手机号不能为空！");
		}
		 if (StringUtils.isBlank(code)) {
			throw new ServiceException("验证码不能为空！");
		}
		 
		SmsCode verificationCode = new SmsCode();
		verificationCode.setMobile(mobile);
		verificationCode.setType(type);
		
		SmsCode code2 = smsMapper.getSmsCode(verificationCode);
		
		//是否该手机号发送过验证码
		if(code2 == null) 
		{
			throw new ServiceException("请先发送验证码！");
		}
		
		long times 		  = code2.getActualTime().getTime();//短信的有效时间毫秒数
		long currentTimes = System.currentTimeMillis();//当前系统时间的毫秒数
		
		//判断验证码是否失效
		if(currentTimes > times)
		{
			throw new ServiceException("验证码已失效！");
		}
		
		//判断验证码是否匹配
		if (!StringUtils.equals(code2.getCode(), code)) {

			throw new ServiceException("验证码错误！");
		}
		
		smsMapper.delete(code2);

	}
}

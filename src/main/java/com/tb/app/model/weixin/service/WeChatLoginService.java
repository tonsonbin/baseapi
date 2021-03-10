package com.tb.app.model.weixin.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolsn.common.weixin.dao.WXDao;
import com.coolsn.common.weixin.entity.WXSPUserEntity;
import com.coolsn.modules.tb.judgeParams.JudgeParams;
import com.coolsn.modules.tb.judgeParams.JudgeParamsResult;
import com.tb.app.common.YamlConfigWeixin;
import com.tb.app.common.exception.ServiceException;
import com.tb.app.common.security.IdGen;
import com.tb.app.common.web.Result;
import com.tb.app.common.web.ResultCode;
import com.tb.app.common.web.ResultGenerator;
import com.tb.app.model.weixin.entity.WXBaseInfo;
import com.tb.app.model.weixin.entity.WXBindMobile;

/**
 * @Author:唐彬
 * @Description:微信小程序登录服务，包含了绑定手机号的逻辑
 * @Data:Created in 2020/06/05
 */
@Service
@Transactional(readOnly = true)
public class WeChatLoginService{

    @Resource
    private WXBaseInfoService wxBaseInfoService;
    @Resource
    private WXBindMobileService wxBindMobileService;
    
    /**
     * 
     * 基本信息授权接口
     * 
     * 1、如果没有绑定手机号会返回需要手机号授权的返回码
     * 
     * @param encryptedData
     * @param iv
     * @param code
     * @return 成功的话data即为手机号
     */
    @Transactional(readOnly = false)
    public Result login(String encryptedData,String iv,String code) {
    	
    	//参数校验>>
    	JudgeParamsResult judgeParamsResult = new JudgeParams()
    			.addParams("encryptedData", encryptedData)
    			.addParams("iv", iv)
    			.addParams("code", code)
    			.verify();
    	if (!judgeParamsResult.success()) {
			throw new ServiceException(judgeParamsResult.getMessage());
		}
    	
    	//初始化微信工具
    	WXDao wxDao = new WXDao().init(YamlConfigWeixin.getAppId(),YamlConfigWeixin.getAppSecret());

    	//数据中空格转换
    	encryptedData = encryptedData.replaceAll(" ","+");
    	//数据中空格转换
    	iv = iv.replaceAll(" ","+");
    	
    	//登录
    	WXSPUserEntity wxspUserEntity = wxDao.wxspLogin(encryptedData, iv, code);
    	
    	if (wxspUserEntity == null) {
    		return ResultGenerator.genFailResult("取微信用户信息失败！");
		}
    	
    	//取得微信用户信息
    	String openId = wxspUserEntity.getOpenId();
    	
    	//根据openId取微信用户信息
    	WXBaseInfo wxBaseInfoQ = new WXBaseInfo();
    	wxBaseInfoQ.setOpenId(openId);
    	wxBaseInfoQ.setAppid(YamlConfigWeixin.getAppId());
    	List<WXBaseInfo> wxBaseInfos = wxBaseInfoService.findList(wxBaseInfoQ);
    	
    	WXBaseInfo wxBaseInfo = new WXBaseInfo();
    	//判断数据库是否存在该微信用户信息
    	if (wxBaseInfos == null || wxBaseInfos.size() == 0) {
			
    		//未找到openId对应的信息，进行用户信息注入
    		wxBaseInfo = new WXBaseInfo();
    		wxBaseInfo.setId(IdGen.uuid());
    		wxBaseInfo.setIsNewRecord(true);
    		wxBaseInfo.preInsert();
    		wxBaseInfo.setCreatetime(new Date());
    			
		}else {
			
			//已经存在了该用户，更新信息
			wxBaseInfo = wxBaseInfos.get(0);
			wxBaseInfo.setIsNewRecord(false);
			wxBaseInfo.preUpdate();
			
		}

    	wxBaseInfo.setCity(wxspUserEntity.getCity());
    	wxBaseInfo.setCountry(wxspUserEntity.getCountry());
    	wxBaseInfo.setHeadimgurl(wxspUserEntity.getAvatarUrl());
    	wxBaseInfo.setNickname(wxspUserEntity.getNickName());
    	wxBaseInfo.setOpenId(openId);
    	wxBaseInfo.setProvince(wxspUserEntity.getProvince());
    	wxBaseInfo.setSex(Integer.valueOf(wxspUserEntity.getGender()));
    	wxBaseInfo.setUnionid(wxspUserEntity.getUnionId());
    	wxBaseInfo.setAppid(YamlConfigWeixin.getAppId());
    	
		wxBaseInfoService.save(wxBaseInfo);
		
		//获取绑定的默认的手机号
    	WXBindMobile wxBindMobile = wxBindMobileService.findDefaultTelUser(openId);
    	if (wxBindMobile == null) {
			
    		//openId未绑定手机号
    		return new Result().setCode(ResultCode.LOGIN_UNBINDPHONE).setMessage("需要手机号授权！");
    		
		}
    	
    	return ResultGenerator.genSuccessResult(wxBindMobile.getMobile());
       
    }

    /**
     * 
     * 微信授权手机号
     * 
     * 一个openId可以绑定多个手机号
     * 但是一个手机号只能绑定到一个openId上
     * 
     * 1、查询该openId是否进行过基本信息入库
     * 
     * @param encryptedData
     * @param iv
     * @param code
     * @return 成功的话，返回的data为手机号
     */
    @Transactional(readOnly = false)
    public Result wxappBindPhone(String encryptedData, String iv, String code) {
    	
    	//参数校验>>
    	JudgeParamsResult judgeParamsResult = new JudgeParams()
    			.addParams("encryptedData", encryptedData)
    			.addParams("iv", iv)
    			.addParams("code", code)
    			.verify();
    	if (!judgeParamsResult.success()) {
			throw new ServiceException(judgeParamsResult.getMessage());
		}

    	//数据中空格转换
    	encryptedData = encryptedData.replaceAll(" ","+");
    	//数据中空格转换
    	iv = iv.replaceAll(" ","+");
    	
    	//初始化微信工具
    	WXDao wxDao = new WXDao().init(YamlConfigWeixin.getAppId(),YamlConfigWeixin.getAppSecret());
    	
    	//登录获取手机号
    	WXSPUserEntity wxspUserEntity = wxDao.wxspLoginGetPhone(encryptedData, iv, code);
    	
    	if (wxspUserEntity == null) {
    		return ResultGenerator.genFailResult("登录失败！");
		}
    	
    	//取得微信用户信息
    	String openId = wxspUserEntity.getOpenId();
    	String phone  = wxspUserEntity.getPhone();

    	//根据openId取数据库微信用户信息>>
    	WXBaseInfo wxBaseInfoQ = new WXBaseInfo();
    	wxBaseInfoQ.setOpenId(openId);
    	wxBaseInfoQ.setAppid(YamlConfigWeixin.getAppId());
    	List<WXBaseInfo> wxBaseInfos = wxBaseInfoService.findList(wxBaseInfoQ);
    	
    	WXBaseInfo wxBaseInfo = null;
    	//判断数据库是否存在该微信用户信息
    	if (wxBaseInfos == null || wxBaseInfos.size() ==0) {
			
    		//未找到openId对应的信息，该用户未进行过授权登录
    		return new Result().setCode(ResultCode.LOGIN_GETUSERINFO).setMessage("授权登录"); 
    			
		}else {
			
			//已经存在了该用户，更新信息
			wxBaseInfo = wxBaseInfos.get(0);
			wxBaseInfo.setIsNewRecord(false);
			
		}
    	
    	//微信基本信息数据入库
    	wxBaseInfo.setOpenId(openId);
    	wxBaseInfo.setUnionid(wxspUserEntity.getUnionId());
    	wxBaseInfo.setMobile(phone);
    	wxBaseInfo.setAppid(YamlConfigWeixin.getAppId());
    	wxBaseInfoService.save(wxBaseInfo);
		
		//绑定
		wxBindMobileService.bindHelp(phone,openId);
		
    	return ResultGenerator.genSuccessResult(phone);
    	
    }

    /**
     * 刷新token
     * 
     * 1、如果数据库未查到基本信息返回失败
     * 2、如果该openid未绑定手机号会返回失败
     * 
     * @param code
     * @return 返回的data为该openId绑定的默认的手机号
     */
    @Transactional(readOnly = false)
    public Result wxappRefreshToken(String code) {
    	
    	//初始化微信工具
    	WXDao wxDao = new WXDao().init(YamlConfigWeixin.getAppId(),YamlConfigWeixin.getAppSecret());
    	
    	//根据code取openId
    	WXSPUserEntity wxspUserEntity = wxDao.wxspLogin(code);
    	if (wxspUserEntity == null) {
    		
    		throw new ServiceException("刷新token失败，请重试！");
    		
		}
    	
    	String openId = wxspUserEntity.getOpenId();
    	//根据openId取数据库微信用户信息>>
    	WXBaseInfo wxBaseInfoQ = new WXBaseInfo();
    	wxBaseInfoQ.setOpenId(openId);
    	wxBaseInfoQ.setAppid(YamlConfigWeixin.getAppId());
    	List<WXBaseInfo> wxBaseInfos = wxBaseInfoService.findList(wxBaseInfoQ);
    	
    	//判断数据库是否存在该微信用户信息
    	if (wxBaseInfos == null || wxBaseInfos.isEmpty()) {
			
    		//未找到openId对应的信息，该用户未进行过授权登录
    		//throw new ServiceException(new Result().setCode(ResultCode.LOGIN_GETUSERINFO).setMessage("授权登录").toString());
    		//返回空
    		return ResultGenerator.genFailResult("未找到该openId对应入库信息！");
		}
    	
    	//获取绑定的默认的手机号
    	WXBindMobile wxBindMobile = wxBindMobileService.findDefaultTelUser(openId);
    	if (wxBindMobile == null) {
    		
    		//openId未绑定手机号
    		//return new Result().setCode(ResultCode.LOGIN_UNBINDPHONE).setMessage("需要手机授权！");
    		return ResultGenerator.genFailResult("该微信用户未绑定手机号！");
    		
		}

    	//获取用户的token>>
    	return ResultGenerator.genSuccessResult(wxBindMobile.getMobile());
            
    }
   
}

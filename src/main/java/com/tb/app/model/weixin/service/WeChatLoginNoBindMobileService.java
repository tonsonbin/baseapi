package com.tb.app.model.weixin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolsn.common.weixin.dao.WXDao;
import com.coolsn.common.weixin.entity.WXSPUserEntity;
import com.coolsn.modules.tb.judgeParams.JudgeParams;
import com.coolsn.modules.tb.judgeParams.JudgeParamsResult;
import com.tb.app.common.YamlConfigWeixin;
import com.tb.app.common.exception.ServiceException;

/**
 * @Author:唐彬
 * @Description:微信小程序登录服务，不需要绑定手机号
 * @Data:Created in 2020/06/05
 */
@Service
@Transactional(readOnly = true)
public class WeChatLoginNoBindMobileService{

    @Resource
    private WXBaseInfoService wxBaseInfoService;
    
    /**
     * 
     * 基本信息授权接口
     * 
     * @param encryptedData
     * @param iv
     * @param code
     * @return WXSPUserEntity 如果返回为空，表示没有取到
     */
    @Transactional(readOnly = false)
    public WXSPUserEntity login(String encryptedData,String iv,String code) {
    	
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
    	return wxDao.wxspLogin(encryptedData, iv, code);
    	
    }
    
}

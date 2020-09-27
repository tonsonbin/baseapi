package com.tb.app.model.weixin.utils.weixin;

import com.coolsn.common.weixin.common.WXImplResult;
import com.coolsn.common.weixin.dao.WXDao;

public class GetAccessToken {

	public static WXImplResult get(String appId,String appSecret) {
		
		return new WXDao().init(appId,appSecret).getAccessToken();
	}
}

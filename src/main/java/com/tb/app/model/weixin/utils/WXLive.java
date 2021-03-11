package com.tb.app.model.weixin.utils;

import com.coolsn.common.weixin.common.WXImplResult;
import com.coolsn.common.weixin.dao.WXDao;
import com.tb.app.common.YamlConfigWeixin;

/**
 * 微信直播
 * @author tangbin
 * @date 2021年3月11日
 */
public class WXLive {

	/**
	 * 取直播间列表
	 * @param start 开始值
	 * @param limit 取的数量
	 * @return WXImplResult data: List:LiveRoom
	 */
	public WXImplResult getLiveRoomList(int start,int limit) {
		
		WXDao wxDao = new WXDao().init(YamlConfigWeixin.getAppId(), YamlConfigWeixin.getAppSecret());
		return wxDao.wxspLiveQueryRoomList(start, limit);
		
	}
}

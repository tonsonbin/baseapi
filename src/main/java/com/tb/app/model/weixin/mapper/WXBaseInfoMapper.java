package com.tb.app.model.weixin.mapper;

import com.tb.app.common.persistence.CrudMapper;
import com.tb.app.model.weixin.entity.WXBaseInfo;

public interface WXBaseInfoMapper extends CrudMapper<WXBaseInfo> {

    /**
     * 根据openid取微信信息
     *
     * @param userWeixinBaseInfoQuery
     * @return
     */
	WXBaseInfo findByOpenId(WXBaseInfo wxBaseInfo);

    /**
     * [**通过微信open删除微信信息**]
     *
     * @param weixinBaseInfo
     * @return int
     * @author Benjamin
     * @date 16:30 2020/10/20
     * @version 1.0
     **/
    int deleteByOpenId(WXBaseInfo wxBaseInfo);
}
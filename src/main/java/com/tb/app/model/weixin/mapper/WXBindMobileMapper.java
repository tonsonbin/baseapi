package com.tb.app.model.weixin.mapper;

import org.apache.ibatis.annotations.Param;

import com.tb.app.common.persistence.CrudMapper;
import com.tb.app.model.weixin.entity.WXBindMobile;

public interface WXBindMobileMapper extends CrudMapper<WXBindMobile> {
	
    /**
     * 将wxbiId对应的非该id的数据设置为非默认
     *
     * @param wxBindMobile
     */
    void unDefault(WXBindMobile wxBindMobile);

    /**
     * 将该数据设置为默认
     *
     * @param wxBindMobile
     */
    void defaultById(WXBindMobile wxBindMobile);

    /**
     * 获取该openId默认的绑定信息
     *
     * @param openId
     * @param appId 
     * @return
     */
    WXBindMobile findDefaultTelUser(@Param("openId") String openId, @Param("appId")String appId);

}
package com.tb.app.model.weixin.entity;

import com.tb.app.common.persistence.DataEntity;

/**
 * 
 */
public class WXBindMobile extends DataEntity<WXBindMobile> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * openId
     */
    private String openId;

    /**
     * 用户手机号
     */
    private String mobile;
    
    /**
     * 状态0默认 1非默认
     */
    private String isDefault;
    
    /**
     * appId
     */
    private String appId;
    
    /**
     * 备注
     */
    private String remark;
    

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
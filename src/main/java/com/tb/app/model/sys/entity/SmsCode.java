package com.tb.app.model.sys.entity;

import java.util.Date;

import com.tb.app.common.persistence.DataEntity;

/**
 * 
 */
public class SmsCode extends DataEntity<SmsCode> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;
    /**
     * 发送类型
			0：添加绑定手机号
			1：修改支付密码
     */
    private String type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 实效时间
     */
    private Date actualTime;

    /**
     * 手机号
     * @return mobile 手机号
     */
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 验证码
     * @return code 验证码
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 实效时间
     * @return actual_time 实效时间
     */
    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    
}
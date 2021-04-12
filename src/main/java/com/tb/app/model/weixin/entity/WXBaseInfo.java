package com.tb.app.model.weixin.entity;

import java.util.Date;

import com.tb.app.common.persistence.DataEntity;

/**
 * 
 */
public class WXBaseInfo extends DataEntity<WXBaseInfo> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * 用户小程序唯一openid
     */
    private String openId;
    
    /**
     * appid用于区分多个小程序
     */
    private String appId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别（1-男；2-女；0-未知）
     */
    private Integer sex;

    /**
     * 用户个人资料填的所属省
     */
    private String province;

    /**
     * 用户个人资料填的所属城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 头像URL
     */
    private String headimgurl;

    /**
     * 用户特权
     */
    private String privilege;

    /**
     * 微信统一联合ID
     */
    private String unionid;

    /**
     * 用户在微信邦定的手机号码
     */
    private String mobile;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
     * 昵称
     * @return nickname 昵称
     */
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 性别（1-男；2-女；0-未知）
     * @return sex 性别（1-男；2-女；0-未知）
     */
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 用户个人资料填的所属省
     * @return province 用户个人资料填的所属省
     */
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 用户个人资料填的所属城市
     * @return city 用户个人资料填的所属城市
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 国家
     * @return country 国家
     */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 头像URL
     * @return headimgurl 头像URL
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 用户特权
     * @return privilege 用户特权
     */
    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * 微信统一联合ID
     * @return unionid 微信统一联合ID
     */
    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
     * 创建时间
     * @return createtime 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 更新时间
     * @return updatetime 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

    
}

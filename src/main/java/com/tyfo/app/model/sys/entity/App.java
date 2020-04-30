package com.tyfo.app.model.sys.entity;

import com.tyfo.app.common.persistence.DataEntity;

/**
 * 
 */
public class App extends DataEntity<App> {
    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用密钥（暂时不用）
     */
    private String appSecret;

    /**
     * 名称
     */
    private String appName;

    /**
     * 应用ID
     * @return app_id 应用ID
     */
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 应用密钥（暂时不用）
     * @return app_secret 应用密钥（暂时不用）
     */
    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * 名称
     * @return app_name 名称
     */
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
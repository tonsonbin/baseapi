package com.tyfo.app.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description yaml 读取
 * @Author Benjamin
 * @CreateDate 2018-12-20 16:06
 **/
@Configuration
public class YamlConfig {
    private static String serverPort;
    private static String serverPath;
    //本服务基本路径
    private static String serverBasePath;
    //轮询平台管理地址
   // private static String xxlJobAdminAddresses;
    //轮询平台项目名称
    //private static String xxlJobAppName;
    //轮询平台端口
    //private static String xxlJobPort;

    public static String getServerPort() {
        return serverPort;
    }

    @Value("${server.port}")
    public void setServerPort(String serverPort) {
        YamlConfig.serverPort = serverPort;
    }

    public static String getServerPath() {

        if (StringUtils.isBlank(serverPath)) {
            if (StringUtils.isBlank(serverPath)) {
                serverPath = "";
            }
        }

        return serverPath;
    }

    @Value("${server.servlet.context-path}")
    public void setServerPath(String serverPath) {
        YamlConfig.serverPath = serverPath;
    }

    @Value("${server.basepath}")
    public void setServerBasePath(String serverBasePath) {
        YamlConfig.serverBasePath = serverBasePath;
    }

    /**
     * 获取服务器访问路径
     *
     * @return
     */
    public static String getServerBasePath() {
        return serverBasePath + serverPath;
    }

    /*public static String getXxlJobAdminAddresses() {
        return xxlJobAdminAddresses;
    }

    @Value("${xxl-job.admin-addresses}")
    public void setXxlJobAdminAddresses(String xxlJobAdminAddresses) {
        YamlConfig.xxlJobAdminAddresses = xxlJobAdminAddresses;
    }

    public static String getXxlJobAppName() {
        return xxlJobAppName;
    }

    @Value("${xxl-job.appName}")
    public void setXxlJobAppName(String xxlJobAppName) {
        YamlConfig.xxlJobAppName = xxlJobAppName;
    }

    public static String getXxlJobPort() {
        return xxlJobPort;
    }

    @Value("${xxl-job.port}")
    public void setXxlJobPort(String xxlJobPort) {
        YamlConfig.xxlJobPort = xxlJobPort;
    }*/
}

package com.tb.app.common;

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
    //api的servletPath根路径
    private static String apiPath;
    //视图的servletPath根路径
    private static String viewPath;
    //文件保存物理路径
    private static String userFilesBaseDir;
    //文件访问虚拟路径
    private static String userFilesBaseUrl;
    //文件服务根地址
    private static String userFilesServer;
    //环境
    private static String projectEnv;

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
    
    @Value("${apiPath}")
    public void setApiPath(String apiPath) {
        YamlConfig.apiPath = apiPath;
    }
    
    @Value("${viewPath}")
    public void setViewPath(String viewPath) {
        YamlConfig.viewPath = viewPath;
    }

    @Value("${userfiles.basedir}")
    public void setUserFilesBaseDir(String userFilesBaseDir) {
        YamlConfig.userFilesBaseDir = userFilesBaseDir;
    }

    @Value("${userfiles.baseUrl}")
    public void setUserFilesBaseUrl(String userFilesBaseUrl) {
        YamlConfig.userFilesBaseUrl = userFilesBaseUrl;
    }

    @Value("${userfiles.server}")
    public void setUserFilesServer(String userFilesServer) {
        YamlConfig.userFilesServer = userFilesServer;
    }
    
    @Value("${project.env}")
    public void setProjectEnv(String projectEnv) {
        YamlConfig.projectEnv = projectEnv;
    }

    /**
     * 获取服务器访问路径
     *
     * @return
     */
    public static String getServerBasePath() {
        return serverBasePath + serverPath;
    }

	public static String getApiPath() {
		return apiPath;
	}

	public static String getViewPath() {
		return viewPath;
	}

	public static String getUserFilesBaseDir() {
		return userFilesBaseDir;
	}

	public static String getUserFilesBaseUrl() {
		return userFilesBaseUrl;
	}

	public static String getUserFilesServer() {
		return userFilesServer;
	}

	public static String getProjectEnv() {
		return projectEnv;
	}
    
	public static boolean beProd() {
		return ProjectConstant.PROJECTENV_PROD.equals(projectEnv);
	}
	
	/**
	 * 将环境设置为正式环境
	 * 主要是用于测试
	 */
	public static void setBeProd() {
		projectEnv = ProjectConstant.PROJECTENV_PROD;
	}
}

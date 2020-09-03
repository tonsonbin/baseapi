package com.tb.app.common.utils.httpSend;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tb.app.common.utils.Constant;

/**
 * 请求链接post和get方式
 * @author tb
 *
 */
public class HttpSend {
	
	static Logger logger = LogManager.getLogger(HttpSend.class);

	static 
    {
      try
      {
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier  
        (
          new HostnameVerifier() 
          {
              @Override
              public boolean verify(String urlHostName, SSLSession session) {
                  // TODO Auto-generated method stub
                  return true;
              }
          }
        );
      } catch (Exception e)  {e.printStackTrace();}
    }
	
	/**
	 * get请求-返回string结果
	 * @param strUrl 请求地址
	 * @param param 请求参数
	 * @param desc 请求描述
	 * @return 结果
	 */
	public static String getSend(String strUrl,String param,String desc){
		logger.info("http ask"+desc);
		logger.info("HttpSend.getSend 访问地址："+strUrl);
		logger.info("HttpSend.getSend 访问参数："+param);
		
		URL url = null;
		HttpURLConnection connection = null;
		if(strUrl.contains("\\?") && StringUtils.isNotBlank(param)) {
			
			strUrl = strUrl+"&&"+param;
			
		}else if(StringUtils.isNotBlank(param)){
			
			strUrl = strUrl+"?"+param;
			
		}
		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		
			reader.close();
			
			String res=buffer.toString();
			logger.info("HttpSend.getSend 返回数据："+res);
			
			return res;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	/**
	 * get请求-返回HttpURLConnection
	 * @param strUrl 请求地址
	 * @param param 请求参数
	 * @param desc 请求描述
	 * @return HttpURLConnection
	 */
	public static HttpURLConnection getSendConnect(String strUrl,String param,String desc){
		logger.info("http ask"+desc);
		logger.info("HttpSend.getSend 访问地址："+strUrl);
		logger.info("HttpSend.getSend 访问参数："+param);
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(strUrl + "?" + param);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.connect();
			return connection;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			
			
		}
	}
	/**
	 * get请求文件
	 * @param strUrl 请求地址
	 * @param param 请求参数
	 * @param desc 请求描述
	 * @param filePath 文件保存地址
	 * @return
	 */
	public static boolean getSendGFile(String strUrl,String param,String desc,String filePath){
		logger.info("http ask"+desc);
		logger.info("HttpSend.getSend 访问地址："+strUrl);
		logger.info("HttpSend.getSend 访问参数："+param);
		logger.info("HttpSend.getSend 文件保存地址："+filePath);
		
		URL url = null;
		HttpURLConnection connection = null;
		if(strUrl.contains("\\?") && StringUtils.isNotBlank(param)) {
			strUrl = strUrl+"&&"+param;
		}else if(StringUtils.isNotBlank(param)){
			strUrl = strUrl+"?"+param;
		}
		try {
			File file = new File(filePath);
			if(!file.exists()) {
				logger.info("HttpSend.getSend 文件保存地址："+filePath+"，文件不存在，创建文件");
				file.getParentFile().mkdirs();
			}
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.connect();
			
			InputStream ins = connection.getInputStream();
			
			
			FileUtils.createFileByIO(file, ins);
			return true;
		} catch (IOException e) {
			
			e.printStackTrace();
			return false;
			
		}finally {
			
			
		}
	}
	/**
	 * 请求post text类型数据
	 * @param strUrl
	 * @param param
	 * @param desc
	 * @return
	 */
	public static String postSend(String strUrl,String param,String desc){
		logger.info("http ask"+desc);
		logger.info("HttpSend.getSend 访问地址："+strUrl);
		logger.info("HttpSend.getSend 访问参数："+param);
		try {
			param = URLEncoder.encode(param,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		URL url = null;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();

			//POST方法时使用
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(param.getBytes("UTF-8"));
			out.flush();
			out.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuilder sBuild = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sBuild.append(line);
			}
		
			reader.close();
			String res=sBuild.toString();
			logger.info("HttpSend.postSend 返回数据："+res);
			return res;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		
	}
	/**
	 * 请求post text类型数据
	 * @param strUrl
	 * @param param
	 * @param desc
	 * @return
	 */
	public static String postSendJson(String strUrl,String param,String desc){
		logger.info("http ask"+desc);
		logger.info("HttpSend.getSend 访问地址："+strUrl);
		logger.info("HttpSend.getSend 访问参数："+param);
		/*try {
			param = URLEncoder.encode(param,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		URL url = null;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-type", "application/json");
			connection.setConnectTimeout(Constant.REQUEST_TIMEOUT.intValue());
			connection.connect();

			//POST方法时使用
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(param.getBytes("UTF-8"));
			out.flush();
			out.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuilder sBuild = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sBuild.append(line);
			}
		
			reader.close();
			String res=sBuild.toString();
			logger.info("HttpSend.postSend 返回数据："+res);
			return res;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		
	}
	/**
	 * 
	 * post文件下载
	 * 
	 * @param strUrl
	 * @param param
	 * @param desc
	 * @param filePath
	 * @return
	 */
	public static boolean postSendGFile(String strUrl,String param,String desc,String filePath){
		logger.info("http ask"+desc);
		logger.info("HttpSend.getSend 访问地址："+strUrl);
		logger.info("HttpSend.getSend 访问参数："+param);
		logger.info("HttpSend.getSend 文件保存地址："+filePath);
		
		URL url = null;
		HttpURLConnection connection = null;
		
		try {
			File file = new File(filePath);
			if(!file.exists()) {
				Log.writeLogForNormal(Log.INFO,"HttpSend.getSend 文件保存地址："+filePath+"，文件不存在，创建文件");
				file.getParentFile().mkdirs();
			}
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();

			//POST方法时使用
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(param.getBytes("UTF-8"));
			out.flush();
			out.close();
			
			InputStream ins = connection.getInputStream();
			
			
			FileUtils.createFileByIO(file, ins);
			
			return true;
		} catch (IOException e) {
			logger.info("HttpSend.getSend 下载文件catch到错误："+e.getLocalizedMessage());
			return false;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
		
	}
public static InputStream postSendStream(String strUrl,String param){
		
		
		URL url = null;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(param.getBytes("UTF-8"));
			out.flush();
			out.close();

			//POST方法时使用
			return connection.getInputStream();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
				}
		}
		
		
	}

public static String postDelete(String strUrl,String param){
	
	
	URL url = null;
	HttpURLConnection connection = null;
	
	try {
		url = new URL(strUrl);
		connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("delete");
		connection.setUseCaches(false);
		connection.connect();

		//POST方法时使用
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.writeBytes(param);
		out.flush();
		out.close();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuilder sBuild = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sBuild.append(line);
		}
	
		reader.close();
		return sBuild.toString();
	} catch (IOException e) {
		return null;
	} finally {
		if (connection != null) {
			connection.disconnect();
			}
	}
	
	
}
	/**
	 * 转为16进制方法
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String paraTo16(String str) throws UnsupportedEncodingException {
			String hs = "";
			
			byte[] byStr = str.getBytes("UTF-8");
			for(int i=0;i<byStr.length;i++)
			{
				String temp = "";
				temp = (Integer.toHexString(byStr[i]&0xFF));
				if(temp.length()==1) temp = "%0"+temp;
				else temp = "%"+temp;
				hs = hs+temp;
			}
			return hs.toUpperCase();
	
		
	}

	public static void main(String[] args){
		
	}
	/**
	 * 返回HttpURLConnection 可用于模拟登录及登录后的操作
	 * @param strUrl
	 * @param param
	 * @return
	 */
	public static HttpURLConnection postSendKeep(HttpURLConnection huc,String strUrl,String param) {
		
		URL url = null;
		HttpURLConnection connection = null;
		
		try {
			url = new URL(strUrl);
			connection = huc==null?(HttpURLConnection) url.openConnection():huc;
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			connection.setRequestProperty("Accept-Charset", "utf-8");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=utf-8");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.write(param.getBytes("UTF-8"));
			out.flush();
			out.close();

			//POST方法时使用
			return connection;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
				}
		}
		
	}
	/**
	 * 获取huc的输出
	 * @param huc
	 * @return
	 */
	public static String getContent(HttpURLConnection huc) {
		
		try {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(huc.getInputStream(), "utf-8"));
		StringBuilder sBuild = new StringBuilder();
		String line = "";
		while ((line = reader.readLine()) != null) {
			sBuild.append(line);
		}
	
		reader.close();
		return sBuild.toString();
		
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (huc != null) {
				huc.disconnect();
				}
		}
		
	}
	/**
     * 覆盖java默认的证书验证
     */
    private static final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }

		@Override
		public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws java.security.cert.CertificateException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
				throws java.security.cert.CertificateException {
			// TODO Auto-generated method stub
			
		}
    }};

    /**
     * 设置不验证主机
     */
    private static final HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    /**
     * 信任所有
     * @param connection
     * @return
     */
    private static SSLSocketFactory trustAllHosts(HttpsURLConnection connection) {
        SSLSocketFactory oldFactory = connection.getSSLSocketFactory();
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            SSLSocketFactory newFactory = sc.getSocketFactory();
            connection.setSSLSocketFactory(newFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oldFactory;
    }

    private static void trustAllHttpsCertificates() throws NoSuchAlgorithmException, KeyManagementException{
        TrustManager[] trustAllCerts = new TrustManager[1]; 
        trustAllCerts[0] = (TrustManager) new TrustAllManager(); 
        SSLContext sc = SSLContext.getInstance("SSL"); 
        sc.init(null, trustAllCerts, null); 
        HttpsURLConnection.setDefaultSSLSocketFactory(
            sc.getSocketFactory());
    }
     
    private static class TrustAllManager implements X509TrustManager {

    	public X509Certificate[] getAcceptedIssuers() 
        {
          return null;
        } 
        public void checkServerTrusted(X509Certificate[] certs, 
            String authType)
          throws CertificateException 
        {
        } 
        public void checkClientTrusted(X509Certificate[] certs, 
            String authType)
        throws CertificateException 
        {
        }
        
    }
}

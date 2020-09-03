package com.tb.app.common.utils;

import com.tb.app.common.security.IdGen;
import okhttp3.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description Okhttps工具类
 * @Author Benjamin
 * @CreateDate 2019-05-24 12:29
 **/
@Component
public class OkHttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpRequest.class);

    private static OkHttpClient okHttpClient;

    @Autowired
    public OkHttpRequest(OkHttpClient okHttpClient) {
        OkHttpRequest.okHttpClient = okHttpClient;
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String get(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        sb.append(parseParams(queries, true));
        Request request = new Request.Builder()
                .url(sb.toString())
                .addHeader("Connection", "close")
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        String responseBody = "";
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection", "close")
                .post(builder.build())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }


    /**
     * post multipart/form-data,支持多文件提交
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String postFromData(String url, Map<String, Object> params) {
        String responseBody = "";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                Object data = params.get(key);
                if (data instanceof File) {
                    File file = (File) data;
                    RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                    builder.addFormDataPart(key, file.getName(), fileBody);
                } else {
                    builder.addFormDataPart(key, String.valueOf(data));
                }
            }
        }
        builder.setType(MediaType.parse("multipart/form-data"));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection", "close")
                .post(builder.build())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * post multipart/form-data,支持多文件提交
     *
     * @param url  请求的url
     * @param key  key
     * @param file file
     * @return
     */
    public static byte[] postSaiDaTarFile(String url, String key, File file) throws IOException {
        String responseBody = "";
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //添加参数
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/x-tar"), file);
        builder.addFormDataPart(key, file.getName(), fileBody);
        builder.setType(MediaType.parse("multipart/form-data"));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection", "close")
                .post(builder.build())
                .build();
        Response response = null;
        byte[] data;
        //此处不进行Tray-catch，将错误抛给上层代码处理，仅限本方法使用，其他方法还是处理一下要好点
        response = okHttpClient.newCall(request).execute();
        data = response.body().bytes();
        response.close();
        return data;
    }


    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String getForHeader(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder()
                .addHeader("Connection", "close")
                .url(sb.toString())
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection", "close")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public static String postXmlParams(String url, String xml) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/xml; charset=utf-8"), xml);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Connection", "close")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }


    /**
     * 下载图片
     *
     * @param request 请求体
     * @return
     */
    private static byte[] download(Request request) {
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().bytes();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return new byte[]{};
    }

    /**
     * 将Map转换为Url
     *
     * @param queries   参数
     * @param firstFlag url 参数
     * @return
     */
    public static String parseParams(Map<String, String> queries, boolean firstFlag) {
        StringBuffer sb = new StringBuffer();
        if (queries != null && queries.keySet().size() > 0) {
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (entry.getValue() == null) {
                    continue;
                }
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        return sb.toString();
    }
}

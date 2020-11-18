package com.tb.app.common.interceptor.http;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * [**请输入类注释**]
 *
 * @ClassName HttpHelper
 * @Author Benjamin
 * @Date 2020/7/15 14:52
 * @Version 1.0
 **/
public class HttpHelper {

    /**
     * [**获取到请求的body值**]
     *
     * @param request 请求
     * @return java.lang.String
     * @author Benjamin
     * @date 14:52 2020/7/15
     * @version 1.0
     **/
    public static String getBodyString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 取传入为json字符串形式的数据
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map<String, String[]> properties = request.getParameterMap();
        // 返回值Map
        Map<String, String> returnMap = new HashMap<String, String>();
        //转换参数
        Iterator<Map.Entry<String, String[]>> entries = properties.entrySet().iterator();

        //遍历参数
        while (entries.hasNext()) {

            Map.Entry<String, String[]> entry = entries.next();
            //参数名称
            String name = (String) entry.getKey();
            //值
            String value = "";

            Object valueObj = entry.getValue();
            //判断值
            if (null == valueObj) {

                //值是null，则设置为空串
                value = "";

            } else if (valueObj instanceof String[]) {

                //数组类型
                String[] values = (String[]) valueObj;
                //遍历值
                for (int i = 0; i < values.length; i++) {
                    //组装为以逗号隔开形式
                    value = values[i] + ",";
                }
                //去掉末尾的逗号
                value = value.substring(0, value.length() - 1);
            } else {

                //普通类型
                value = valueObj.toString();
            }

            returnMap.put(name, value);
        }
        return returnMap;
    }
}

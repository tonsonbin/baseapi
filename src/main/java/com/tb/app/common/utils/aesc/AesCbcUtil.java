package com.tb.app.common.utils.aesc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * Created by yfs on 2017/2/6.
 * <p>
 * AES-128-CBC 加密方式
 * 注：
 * AES-128-CBC可以自己定义“密钥”和“偏移量“。
 * AES-128是jdk自动生成的“密钥”。
 */
public class AesCbcUtil {
	
	static Logger log = LoggerFactory.getLogger(AesCbcUtil.class);

	static{
		
		Security.addProvider(new BouncyCastleProvider());
		
	}
	
	public static void main(String[] args) throws Exception {
		/*String data = "u9DO3JOHvsCTTHnciMXlPmzcQr8JLWqIJbXSqOy29sgrkSowIkEjJ3MuS7w7wNvAVHCxZ1du2GfZXgcCIgpKptSfUfRJqIy1ufkHGoT1lf7E73Ok6FULGRLT+KAwIbwafA/+8UTRIeqwfJ6mv1JGPByeAFdSrCEzDY9rxqgGn/mjTGTj/El4q1zQ2nhWKnlWxWwo8SPJB/Hy+NcsQ0mm8yUxHXi5+SJfsqX/w4mvu1nfxcUxr1lfMyP/65/mf++vkj5WUM4u9+PTQ2Fp6gTtYmwV4wtG9NoKlkQY/A2/hKNYSsNrOF4gKgPjbSEmHwfNluFg8nmTwW7T+6XehgVoQFuLBqVVnSRvr3snrkwruFd/yCx+CmY+nCj1mf4eapbSGOo621o54r1gJ+LTa7xIl9+/TAf68PgqM83mA7Nwg3oy0RtiNcVbk9z5NpMMhRjP0IJ/eaFramid+TTkH0kalo71m6gLhlSUHwqITk25qJvFtWSYCbevcauJn5gtOYQi";
		String iv = "GP1G4FO8WtozKB3h8JTTwA==";
		String key = "J5ut+AxXFNdnQ0+/RxQQmQ==";
		String data2 = decryptMode2(data,key,iv,"utf-8");
		System.out.println(data2);*/
		
		
		/*String data1 = encryptMode1("nested exception is org.apache.ibatis.type.TypeException: JDBC requires that the JdbcType must be specified for all nullable parameters.就是测试433333333333gwefwa4g4wfwqf4","7654323333333333");
		System.out.println(data1);
		String data3 = decryptMode1(data1,"7654323333333333","utf-8");
		System.out.println(data3);*/
		//System.out.println(decryptMode1("cPjfhI4yHlEiKkY/ZyC6NK+cL32/0dKcyGgFkIM5whPH7+lVN1cC1gvs5ctprwYS4ir595G6XEoM\r\nDb9YGz6/CHboiDJ0cmCBWoxRyEk37vw=", Constant.SERVERINFO_E_KEY,"utf-8"));
	}
    /**
     * mode1-解密
     * 
     * AES解密模式:ECB;填充:PKCS5Padding;数据块:128位;
     *
     * @param data           //密文，被加密的数据
     * @param key            //秘钥-16位key
     * @param encodingFormat //解密后的结果需要进行的编码
     * @return
     * @throws IOException 
     * @throws Exception
     */
    public static String decryptMode1(String data, String key, String encodingFormat) throws IOException{
//        initialize();
    	
        //被加密的数据
        byte[] dataByte = new BASE64Decoder().decodeBuffer(data);


        try {
        	Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");


        	cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(encodingFormat), "AES"));


        	byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
        } catch (Exception e) {
            
        	log.error("aesCbcUtil error info: "+e.getLocalizedMessage());
        	
        }

        return null;
    }
    /**
     * mode1-加密
     * 
     * AES加密模式:ECB;填充:PKCS5Padding;数据块:128位;
     * @param str 加密串
     * @param key 秘钥 16位key
     * @return
     * @throws Exception
     */
    public static String encryptMode1(String str, String key) throws Exception {
    	
        if (str == null || key == null) return null;
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES"));
        
        byte[] bytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));
        
        return new BASE64Encoder().encode(bytes);
    }
    /**
     * mode2-加密
     * 
     * AES加密
     * 
     * @param content
     *            需要被加密的字符串
     * @param password
     *            加密需要的密码
     * @return 密文
     */
    public static String encryptMode2(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
                                                                    // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
                                                            // null。

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encodeBase64String(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * mode2-解密
     * 
     * AES解密
     *
     * @param data           //密文，被加密的数据
     * @param key            //秘钥
     * @param iv             //偏移量
     * @param encodingFormat //解密后的结果需要进行的编码
     * @return
     * @throws Exception
     */
    public static String decryptMode2(String data, String key, String iv, String encodingFormat){
//        initialize();
    	System.out.println(data);
        //被加密的数据
        byte[] dataByte = Base64.decodeBase64(data);
        //加密秘钥
        byte[] keyByte = Base64.decodeBase64(key);
        //偏移量
        byte[] ivByte = Base64.decodeBase64(iv);


        try {
        	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");

            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));

            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化

            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, encodingFormat);
                return result;
            }
            
            return null;
        } catch (Exception e) {
            
        	log.error("aesCbcUtil error info: "+e.getLocalizedMessage());
        	
        }

        return null;
    }
    
    
}
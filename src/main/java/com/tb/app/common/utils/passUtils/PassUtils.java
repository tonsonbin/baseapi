package com.tb.app.common.utils.passUtils;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.Validate;

import com.coolsn.common.weixin.common.MD5Tools;

/**
 * 密码处理工具-从coolsn加密方式中分离
 * @author tb
 *
 */
public class PassUtils {
	
	private static final int SALT_SIZE=8;
	private static final int HASH_INTERATIONS=1024;
	private static final String SHA1 = "SHA-1";
	

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 * @throws Exception 
	 */
	public static String getAfterPass(String plainPassword) throws Exception{
		byte[] salt = generateSalt(SALT_SIZE);
		//cdb17a52137e58a482985b8c28b08ded54bda925a0abdf73ed1631e3
		//byte[] salt = Encodes.decodeHex("74d7072bfa34d5285523c3a5fc4b2b65fe65a8b0d1963bdd317144ed".substring(0,16));
		byte[] hashPassword = sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		return encodeHex(salt)+encodeHex(hashPassword);
	}
	/**
	 * 密码匹配
	 * @param serverPass 数据库取出的密码
	 * @param clientPass 客户端传过来的密码
	 * @return
	 * @throws Exception
	 */
	public static boolean equalsPass(String serverPass,String clientPass) throws Exception{
		byte[] salt = decodeHex(serverPass.substring(0,16));
		byte[] hashPassword = sha1(clientPass.getBytes(), salt, HASH_INTERATIONS);
		return (encodeHex(salt)+encodeHex(hashPassword)).equals(serverPass);
	}
	public static void main(String[] args) throws Exception{
		System.out.println(getAfterPass("123456")+"--"+MD5Tools.MD5("123456"));
	}
	
	/**
	 * Hex编码.
	 */
	private static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}
	public static byte[] sha1(byte[] input, byte[] salt, int iterations) throws Exception {
		return digest(input, SHA1, salt, iterations);
	}
	/**
	 * Hex解码.
	 * @throws Exception 
	 */
	private static byte[] decodeHex(String input) throws Exception {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new Exception("内部错误："+e.getLocalizedMessage());
		}
	}
	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 * @throws Exception 
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) throws Exception {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw new Exception("内部错误："+e.getLocalizedMessage());
		}
	}
	/**
	 * 生成随机的Byte[]作为salt.
	 * 
	 * @param numBytes byte数组的大小
	 */
	private static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}
}

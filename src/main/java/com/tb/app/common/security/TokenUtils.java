package com.tb.app.common.security;

/**
 * token生成类
 */
public class TokenUtils {

    /**
     * 过期时间【单位：秒】-30天
     */
    private static final int DURATION = 2592000;

    /**
     * 生成一个Token密钥
     *
     * @param sb
     * @return
     */
    public static String generateToken(String sb) {
        String token = JwtUtil.buildJWT(sb, IdGen.uuid(), DURATION);
        System.out.println("用户:" + sb + "生成的Token：" + token);
        return token;
    }

    /**
     * 检查token合法性
     *
     * @param token
     * @return
     */
    public static Boolean checkToken(String token) {
        return JwtUtil.checkJWT(token);
    }

}

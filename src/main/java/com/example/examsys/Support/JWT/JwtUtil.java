package com.example.examsys.Support.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    //用户权限级别
    private static final  HashMap<String,Integer> powerMap = new HashMap<String,Integer>(){
        {
            put("student", 1);
            put("teacher", 2);
            put("admin", 3);
            put("user", 3);
        }
    };

    /**
     * 过期时间30分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * jwt 密钥
     */
    private static final String SECRET = "jwt_secret";

    /**
     * 生成签名，五分钟后过期
     * @param userId
     * @return
     */
    public static String sign(String userId,String userType) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    // 将 user id 保存到 token 里面
                    .withAudience(userId)
                    // 五分钟后token过期
                    .withExpiresAt(date)
                    // 放payload
                    .withClaim("userType",userType)
                    // token 的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据token获取userId
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 校验token,在这里判断用户权限
     * @param token
     * @return
     */
    public static boolean checkSign(String token,int requireLevel) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            //取出payload,与权限图谱匹配权限
            int userPower = powerMap.get(jwt.getClaim("userType").asString());
            if(userPower<requireLevel){
                throw new IllegalAccessException("权限不足");
            }
            return true;
        } catch (JWTVerificationException | UnsupportedEncodingException exception) {
            throw new RuntimeException("token 无效，请重新获取");
        } catch (IllegalAccessException exception){
            throw  new RuntimeException("权限不足");
        }
    }
}
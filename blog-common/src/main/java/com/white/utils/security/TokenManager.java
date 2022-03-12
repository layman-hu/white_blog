package com.white.utils.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "white.token")
public class TokenManager {

    //秘钥
    private String secretKey;
    //有效时长（毫秒）
    private Integer validTime;
    //头信息
    private String header;

    /**
     * 生成JWT Token 字符串
     *
     * @param userId        签发人ID
     * @param username      签发名
     * validDate            过期时间 签发时间
     * claims               额外添加到荷部分的信息
     *                      例如可以添加用户名、用户ID、用户（加密前的）密码等信息
     *
     */
    public String generateTokenByUserInfo(Integer userId, String username ){
        Date nowDate = new Date();

        //过期时间
        Date validDate = new Date(nowDate.getTime() + validTime);

        //额外信息
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",String.valueOf(userId));
        claims.put("username",username);

                //创建JWT对象
        return Jwts.builder()
                //设置头部信息
                .setHeaderParam("typ","JWT")
                //设置私有声明
                .setClaims(claims)
                //设置payload签发时间
                .setIssuedAt(nowDate)
                //设置payload过期时间
                .setExpiration(validDate)
                //设置安全密钥（生成签名所需的密钥和算法）
                .signWith(SignatureAlgorithm.HS512,secretKey)
                //生成token（1.编码Header和Payload 2.生成签名 3.拼接字符串）
                .compact();
    }

    /**
     * 解析token
     * JWT Token 由 头部 荷载部 和 签名部 三部分组成。签名部分是由加密算法生成，无法反向解密。
     * 而 头部 和 荷载部分是由 Base64 编码算法生成，是可以反向反编码回原样的。
     * 这也是为什么不要在 JWT Token 中放敏感数据的原因。
     *
     * @param token 加密后的token
     * @return claims 返回荷载部分的键值对
     */
    public Claims getClaimByToken(String token){
        try {
                    //构建解析对象
            return Jwts.parser()
                    //设置安全密钥（生成签名所需的密钥和算法）
                    .setSigningKey(secretKey)
                    //解析token
                    .parseClaimsJws(token)
                    //获取payload部分内容
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否过期
     * @param validDate
     * @return
     */
    public boolean isTokenValid(Date validDate){
        return validDate.before(new Date());
    }
}

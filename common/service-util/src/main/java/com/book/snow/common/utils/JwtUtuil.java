/**
 * Author: AKAYWJ
 * Date:2024/3/21 下午3:08
 */
package com.book.snow.common.utils;

import com.book.snow.model.user.GoogleUser;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

public class JwtUtuil {

    private static long time = 1000 * 60 * 60 * 1; // Token过期时间 1H
    private static String sign = "Bearer ";

    public static String createToken(GoogleUser user){
        //创JWTByuilder对象
        JwtBuilder jwtBuilder = Jwts.builder();
        //jwtToken -> abc.def.xxx
        String jwtToken = jwtBuilder
                //Header:头部
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //Payload：载荷
                .claim("userAccount",user.getUserAccount())
                .claim("userId",user.getId())
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time)) //设置Token过期时间
                .setId(UUID.randomUUID().toString()) //设置id字段
                //Sign : 签名
                .signWith(SignatureAlgorithm.HS256,sign) //设置加密算法和签名
                //使用 "." 链接
                .compact();
        return jwtToken;
    }

    public static boolean checkToken(String token){
        if(token == null || token == ""){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(sign).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println("token过期");
            return false;
        }
    }

    //解析
    public static Claims parseJwt(String token){

        JwtParser jwtParser = Jwts.parser(); //获取Jwt解析对象
        //类似Map集合
        Jws<Claims> claimsJws = jwtParser.setSigningKey(sign).parseClaimsJws(token);//解析成为键值对形式
        //获取Jws对象中的数据：get(Key)
        Claims body = claimsJws.getBody();//存储用户保存的数据
        return body;
    }
}

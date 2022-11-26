package com.virgil.nenuoj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import java.util.Calendar;

public class JWTUtil {

    private static final String secret = "virgil";

    /**
     * @param token 用户的 Token
     * @return result 0 为验证成功， 1 为签名错误（即用户错误），2 为令牌过期
     */

    public static int verify( String token ) {
        try {
            JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token);
            return 0;
        }
        catch (SignatureVerificationException e){
            return 1;
        }
        catch (TokenExpiredException e){
            return 2;
        }
    }


    public static String buildToken( String username ){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 2);
        return JWT.create()
                .withClaim("username", username)
                .withIssuer("virgil")
                .withExpiresAt(calendar.getTime())
                .sign(algorithm);
    }

}
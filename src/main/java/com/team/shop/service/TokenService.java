package com.team.shop.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team.shop.bean.User;
import com.team.shop.bean.t_User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    /**
     *  为用户签发token,在密码改变后失效
     * @param user 需要获取token的用户
     * @return token
     */
    public String getToken(User user){
        String token = "";
        token = JWT.create().withAudience(user.getUserId() + "")
                .sign(Algorithm.HMAC256(user.getUserPassword()));
        return token;
    }
}

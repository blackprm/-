package com.team.shop.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.team.shop.annotation.PassToken;
import com.team.shop.bean.User;
import com.team.shop.exception.NoTokenException;
import com.team.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@ResponseBody
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(! (handler instanceof HandlerMethod)){
            // 不是处理器方法时直接放行
            return true;
        }


        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        boolean annotationPresent = method.isAnnotationPresent(PassToken.class);
        System.out.println(annotationPresent);
        System.out.println("访问的方法名字是 "+ method.getName());
        if(annotationPresent){
            // 不需要认证直接放行
            return true;
        }


        String token = request.getHeader("token");



        System.out.println("拦截器中的 token " + token);



        if(token == null){
            // 没有token 直接拦截
            throw new NoTokenException("无token，请重新登录");
        }

        /**
         * 以下进行token验证
         */
        Integer userId = null;

        try{
            userId = Integer.parseInt(JWT.decode(token).getAudience().get(0));
        }catch (Exception e){

            throw new NoTokenException("该用户不存在!非法访问!");
        }
        // 从负载中获取用户id
        User u = userService.getUser(userId);
        if(u == null){

            throw new NoTokenException("该用户不存在!非法访问!");
        }

        // 验证token
        String userPassword = u.getUserPassword();
        System.out.println(userPassword);
        JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256(userPassword)).build();
        try {
            jwtVerifier.verify(token);
        }catch (Exception e){
            throw new NoTokenException("该用户不存在!非法访问!");
        }


        return true;



    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}

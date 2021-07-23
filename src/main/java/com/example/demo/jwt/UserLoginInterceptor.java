package com.example.demo.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.mapper.UserMap;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    UserMap usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws JSONException {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有NeedLogin注释
        if (method.isAnnotationPresent(LoginToken.class)) {
            DecodedJWT tokenInfo = JwtUitl.getTokenInfo(token);
            if(token!=null && tokenInfo!=null){
                User user = usersService.findById(JwtUitl.getUserId(tokenInfo)).get();
                //验证是否修改过密码
                if(!JwtUitl.isUpdatedPassword(tokenInfo,user)){
                    //如果需要重新创建一个token 则通知客户端保存新的toekn 并且将新的token返回
                    if(JwtUitl.needCreate(tokenInfo)){
                        JSONObject tokenJson = new JSONObject();
                        tokenJson.put("token",JwtUitl.createToken(user));
                       // result(response, Result.success(tokenJson));

                        return false;
                    }else{
                        return true;
                    }
                }
            }
            //验证未通过
           // result(response,Result.failure("token_error"));
            return false;
        }
        return true;
    }



}


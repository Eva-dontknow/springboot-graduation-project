package com.example.springbootgraduationproject.interceptor;

import com.example.springbootgraduationproject.component.EncryptComponent;
import com.example.springbootgraduationproject.component.MyToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    //实现了该接口以后，需要在进入controller方法之前完成拦截和校验，
    //所以需要重写 preHandle方法
    @Autowired
    private EncryptComponent encrypt;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Optional.ofNullable(request.getHeader(MyToken.AUTHORIZATION))
                //对token中的auth解密，该方法返回的值token映射到下一个容器中
                .map(auth -> encrypt.decryptToken(auth))
                //token如果存在，则把token放到请求体上
                .ifPresentOrElse(token -> {
                    request.setAttribute(MyToken.UID, token.getUid());
                    request.setAttribute(MyToken.ROLE, token.getRole());
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录");
                });

        return true;
    }
}
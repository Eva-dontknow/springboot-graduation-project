package com.example.springbootgraduationproject.component;

import com.example.springbootgraduationproject.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Slf4j
//当获取真正的用户参数的时候，方法参数会很长，所以对此进行简化
public class RequestComponent {
    public int getUid() {
        //RequestContextHolder 方法可以直接获取当前的线程，
        // currentRequestAttributes：获取当前线程所有的attribute
        return (int) RequestContextHolder.currentRequestAttributes()
                //从所有的attribute中获取指定的attribute，
                // RequestAttributes.SCOPE_REQUEST线程级别，即在请求范围内获取
                .getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST);
    }

    public User.Role getRole() {
        return (User.Role) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }

}
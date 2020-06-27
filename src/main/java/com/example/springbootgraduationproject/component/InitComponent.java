package com.example.springbootgraduationproject.component;

import com.example.springbootgraduationproject.entity.*;
import com.example.springbootgraduationproject.repository.*;
import com.example.springbootgraduationproject.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//把系统管理员信息在系统运行时就存入到系统中
@Component
@Slf4j
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userService;
    @Override
    //afterPropertiesSet回调方法,当系统达到可运行状态时会执行该方法
    //所以此时可以添加默认的管理员信息
    public void afterPropertiesSet() throws Exception {
        int num = 1001;//模拟账号
        //基于编号看当前账号下的用户是否存在，然后决定是否是初始化
        User user = userService.getUser(num);
        if(user == null){
            User u = new User();
            u.setName("春娇");
            u.setNumber(num);
            u.setRole(User.Role.TEACHER);
            u.setPassword(encoder.encode(String.valueOf(num)));
            Teacher t = new Teacher();
            t.setUser(u);//忘写，出错： Caused by: org.springframework.orm.jpa.JpaSystemException:
            // attempted to assign id from null one-to-one property [com.example.springbootgraduationproject.entity.Teacher.User];
            // nested exception is org.hibernate.id.IdentifierGenerationException: attempted to assign id from null one-to-one property
            userService.addTeacher(t);
        }
    }
}

package com.example.springbootgraduationproject.controller;

import com.example.springbootgraduationproject.component.EncryptComponent;
import com.example.springbootgraduationproject.component.MyToken;
import com.example.springbootgraduationproject.entity.User;
import com.example.springbootgraduationproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.security.KeyPairGenerator;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class LoginController {
    @Value("${my.teacher}")
    private String roleTeacher;//对不同角色渲染不同界面
    @Value("${my.student}")
    private String roleStudent;//对不同角色渲染不同界面
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encrypt;

    @PostMapping("login")
    public Map login(@RequestBody User login, HttpServletResponse response) {
        //登录判断，先判断该账号对应的user是否存在
        User user = Optional.ofNullable(userService.getUser(login.getNumber()))
              //存在则进行密码校验
                .filter(u -> encoder.matches(login.getPassword(), u.getPassword()))
              //不存在直接返回空的optional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名密码错误"));
        //基于以上得到的user创建token
        MyToken token = new MyToken(user.getId(), user.getRole());
        //对token进行加密
        String auth = encrypt.encryptToken(token);
        //需要把加密后的token放入响应的头，通过头返给客户端
        //auth通过方法的头返回给客户端，（MyToken.AUTHORIZATION, auth）自定义键值对
        response.setHeader(MyToken.AUTHORIZATION, auth);

        String roleCode = user.getRole() == User.Role.TEACHER ? roleTeacher : roleStudent;
       //KeyPairGenerator.getInstance("RSA").generateKeyPair();//可以获得秘钥对
        return Map.of("role", roleCode);
    }
}
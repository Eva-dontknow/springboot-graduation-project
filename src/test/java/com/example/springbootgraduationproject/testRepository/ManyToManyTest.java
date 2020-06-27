package com.example.springbootgraduationproject.testRepository;


import com.example.springbootgraduationproject.entity.User;
import com.example.springbootgraduationproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static com.example.springbootgraduationproject.entity.User.Role.STUDENT;
import static com.example.springbootgraduationproject.entity.User.Role.TEACHER;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class ManyToManyTest {
    @Autowired
    private UserService userService;

    @Test
    public void test_init() {
        //初始学生1
        User u = new User();
        u.setRole(STUDENT);
        u.setName("王");
        userService.addUser(u);

        User u1 = new User();
        u1.setRole(TEACHER);
        u1.setName("张");
        userService.addUser(u1);
        /*遇到的问题：java.lang.StackOverflowError
         解决: userService 写错了
         遇到的问题：org.springframework.dao.InvalidDataAccessResourceUsageException: could not execute statement; SQL [n/a]; nested exception is org.hibernate.exception.SQLGrammarException: could not execute statement
                   Caused by: org.hibernate.exception.SQLGrammarException: could not execute statement
         解决：？？？？不知道怎么就对了，可能是User下的id是Integer类型，而student和teacher下的id是int类型，但是我也没改。。。。
         遇到的问题：部分实体类无法映射到数据库

         */
    }
    @Test
    public void test_rel() {
        //id为1的学生选择了id为1的课程

    }
}

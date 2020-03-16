package com.example.springbootgraduationproject.example05;

import com.example.springbootgraduationproject.example05.entity.User05;
import com.example.springbootgraduationproject.example05.repository.User05Repository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value = false)
public class User05RepositoryTest {
    @Autowired
    private User05Repository user05Repository;
    @Test
    public void test_addUser(){
        User05 user05 = new User05();//新建状态
        user05.setName("BO");
        user05Repository.save(user05);//可以把新建状态塞到持久化上下文，当整个方法结束后，即事务结束后，同步能进行
        log.debug("shgks");
    }
}

package com.example.springbootgraduationproject.service;

import com.example.springbootgraduationproject.entity.Teacher;
import com.example.springbootgraduationproject.entity.User;
import com.example.springbootgraduationproject.repository.TeacherRepository;
import com.example.springbootgraduationproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
   /* public Teacher addTeacher(User user, Teacher teacher) {
        //由于teacher和user是一对一关系，持久化teacher时必须先持久化user
        //可以使用cascade = CascadeType.PERSIST操作
        userRepository.save(user);
        teacher.setUser(user);
        return teacherRepository.save(teacher);
    }*/
   public Teacher addTeacher(Teacher teacher){
       return teacherRepository.save(teacher);
   }
    //查用户
    public User getUser(int num) {
        return userRepository.find(num);
    }

    public Teacher getTeacher(int tid) {
        return teacherRepository.findById(tid).orElse(null);
    }

    public Teacher updateTeacher(int maxNum, int minRank, int tid) {
        Teacher t = teacherRepository.findById(tid)
                .orElseThrow();
        t.setActualNum(maxNum);
        t.setMinRank(minRank);
        return t;
    }
}

package com.example.springbootgraduationproject.service;

import com.example.springbootgraduationproject.entity.*;
import com.example.springbootgraduationproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private ElectiveRepository electiveRepository;

    public void addUser(User user){
        userRepository.save(user);
    }
    //改密
    public User  updatePwd(int number,String password){
        User u = userRepository.findById(number);
        u.setPassword(password);
        return u;
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
    public Student getStudent(int sid){return studentRepository.findById(sid).orElse(null);
    }
    public Teacher updateTeacher(int maxNum, int minRank, int tid) {
        Teacher t = teacherRepository.findById(tid)
                .orElseThrow();
        t.setActualNum(maxNum);
        t.setMinRank(minRank);
        return t;
    }
    public List<Direction> getDirections(){
        return directionRepository.findAll();
    }

    public void chooseTutor(Teacher t,int sid){
        List<Elective> scs = electiveRepository.findStudent(sid);
    }

    public void addSDirection(Student s,List<Direction> directions){
        String str="";
        int i;
        for(i=0;i<directions.size()-1;i++){
            str = str + directions.get(i).getName()+",";
        }
        str = str+directions.get(i).getName();
        s.setMydirct(str);
        studentRepository.save(s);
    }
}

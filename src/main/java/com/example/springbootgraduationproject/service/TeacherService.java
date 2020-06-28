package com.example.springbootgraduationproject.service;

import com.example.springbootgraduationproject.entity.*;
import com.example.springbootgraduationproject.repository.*;
import com.example.springbootgraduationproject.utils.excute;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.beans.Encoder;
import java.security.PrivateKey;
import java.util.*;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private ElectiveRepository electiveRepository;
    @Autowired
    private PasswordEncoder encoder;

    //添加老师
    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }
    //获取所有老师
    public List<Teacher> getTeacher(){
        List<Teacher> tutors = teacherRepository.findAll();
        return tutors;
    }

    public Student addStudent(Student s, int tid){
        Student student = Optional.ofNullable(studentRepository.find(s.getUser().getName(),s.getUser().getNumber()))
                .orElseGet(()->{
                    User u = s.getUser();
                    u.setPassword(encoder.encode(String.valueOf(s.getUser().getNumber())));
                    u.setRole(User.Role.STUDENT);
                    return s;
                });
        if(student.getTeacher()!=null){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
        Teacher t = teacherRepository.findById(tid).orElseThrow();
        int number = t.getMaxNum();
        t.setMinRank(number+1);
        student.setTeacher(new Teacher(tid));
        studentRepository.save(student);
        return student;
    }
    //删除课程
    public void deleteCourse(int id){
        courseRepository.deleteById(id);
    }
    //更改方向
    public void updateDirections(List<Direction> newDirections){
        directionRepository.deleteAll();
        for(int i=0;i<newDirections.size();i++){
            directionRepository.save(newDirections.get(i));
        }
    }
    //修改指定课程的课程比重
    public Course updateCourse (int id,String name,double weight){
        Course c = courseRepository.findById(id).orElseThrow();
        c.setName(name);
        c.setWeight(weight);
        return c;
    }
    //学生-课程-成绩关联
    public void buildStudents(List<Elective> scs){
        int cid = scs.get(0).getCourse().getId();
        List<Elective> nowscs = electiveRepository.findAll();
        for(Elective sc:nowscs){
            if(sc.getCourse().getId()==cid){
                electiveRepository.delete(sc);
            }
        }
        for (Elective sc:scs) {
            Student student = Optional.ofNullable(studentRepository.find(sc.getStudent().getUser().getName(),sc.getStudent().getUser().getNumber()))
                    .orElseGet(()->{
                        Student s = sc.getStudent();
                        User u = s.getUser();
                        u.setPassword(encoder.encode(String.valueOf(s.getUser().getNumber())));
                        u.setRole(User.Role.STUDENT);
                        return s;
                    });
            sc.setStudent(student);
            electiveRepository.save(sc);
        }
    }
    public List<Integer> excuteStudent(Teacher teacher){
        //制造当前老师选择的课的当前课程id数组
        int n = teacher.getCourses().size();
        Integer tcoursesIds[] = new Integer[n];
        for (int i=0;i<n;i++){
            tcoursesIds[i] =  teacher.getCourses().get(i).getId();
        }
        //制造键为学号，值为分数的map
        //只操作sc表中课程id为课程id数组内部id的sc，计算最终分数
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        List<Elective> scs = electiveRepository.findAll();
        for(Elective sc:scs){
            for(int cid:tcoursesIds){
                if(sc.getCourse().getId()==cid){
                    double t =0;
                    int id = sc.getStudent().getUser().getNumber();
                    if(map.get(id)!=null){
                        t = map.get(id);
                    }
                    map.put(id, (t+(sc.getCourse().getWeight())*(sc.getGrade())));
                }
            }
        }
        for(Integer number:map.keySet()){
            int id = userRepository.find(number).getId();
            //找出当前学生的所有选课记录
            List<Elective> theStudentSCs = electiveRepository.findStudent(id);
            int courseNum=0;
            //取当前学生的所有选课与该老师所教课的交集（找出该学生选的该老师的课的所有选课记录）
            //得出这个学生选了这个老师多少门课
            for(Integer t_cid:tcoursesIds)
                for (Elective sc:theStudentSCs){
                    if(sc.getCourse().getId()==t_cid)
                        courseNum++;
                }
            double score = map.get(number)/courseNum;
            map.put(number, score);
        }
        //map基于value值的降序
        map =  excute.sortByValueDescending(map);//引入函数文件
        System.out.println("基于value值的降序，排序输出结果为：");
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        //返回排完序后，只有键(学号)的数组
        List<Integer> mapKeyList = new ArrayList<Integer>(map.keySet());
//        int maxStuScope = tutor.getScopeStuNum();
//        mapKeyList = mapKeyList.subList(0, maxStuScope);
        System.out.println("mapKeyList:"+mapKeyList);
        return mapKeyList;



    }
}


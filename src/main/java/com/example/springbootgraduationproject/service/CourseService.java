package com.example.springbootgraduationproject.service;

import com.example.springbootgraduationproject.entity.Course;
import com.example.springbootgraduationproject.entity.Student;
import com.example.springbootgraduationproject.entity.Teacher;
import com.example.springbootgraduationproject.entity.User;
import com.example.springbootgraduationproject.repository.CourseRepository;
import com.example.springbootgraduationproject.repository.StudentRepository;
import com.example.springbootgraduationproject.repository.TeacherRepository;
import com.example.springbootgraduationproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public Course addCourse(Course c) {
        return courseRepository.refresh(courseRepository.save(c));
    }

    public List<Course> listCourses(int tid) {
        return teacherRepository.getOne(tid).getCourses();
    }
    public Course getCourse(int cid, int uid) {
        return courseRepository.find(cid, uid);
    }

    /**
     * 对user声明了persist/remove级联操作
     * 缺少判断该生已经被其他老师选择，可以抛个异常
     * @param s
     * @param tid
     * @return
     */
    public Student addStudent(Student s, int tid) {
        Student student = Optional.ofNullable(studentRepository.find(s.getUser().getName(), s.getUser().getNumber()))
                .orElseGet(() -> {
                    User u = s.getUser();
                    u.setPassword(encoder.encode(String.valueOf(s.getUser().getNumber())));
                    u.setRole(User.Role.STUDENT);
                    return s;
                });

        student.setTeacher(new Teacher(tid));
        /*加一个判断，判断该学生是否与其他老师建立联系*/
        /*
        *
        * */
        studentRepository.save(student);
        // studentRepository.refresh(student);
        return student;
    }
}
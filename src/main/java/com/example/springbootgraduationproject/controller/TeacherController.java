package com.example.springbootgraduationproject.controller;


import com.example.springbootgraduationproject.component.MyToken;
import com.example.springbootgraduationproject.component.RequestComponent;
import com.example.springbootgraduationproject.entity.*;
import com.example.springbootgraduationproject.service.CourseService;
import com.example.springbootgraduationproject.service.TeacherService;
import com.example.springbootgraduationproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("welcome")
    public void getIndex() {
        log.debug("{}", requestComponent.getUid());
    }
   /* @GetMapping("welcome1")//@GetMapping("welcome")方法的另一种写法
    public void getIndex1(HttpServletRequest request) {
        log.debug("{}", (int)request.getAttribute(MyToken.UID));
    }
    @GetMapping("welcome2")//@GetMapping("welcome")方法的另一种写法
    public void getIndex2(@RequestAttribute(MyToken.UID) int uid) {
        log.debug("{}", uid);
    }*/

    @GetMapping("index")
    public Map getTeacher() {
        Teacher t = userService.getTeacher(requestComponent.getUid());
        return Map.of(
                "teacher", t,
                "courses", t.getCourses(),
                "students", t.getStudents());
    }
    @PostMapping("courses")//添加课程
    public Map postCourse(@RequestBody Course course) {
        course.setTeacher(new Teacher(requestComponent.getUid()));
        courseService.addCourse(course);
        return Map.of("course", course);
    }

    @GetMapping("courses/{cid}")
    public Map getCourse(@PathVariable int cid) {
        return Map.of("course", courseService.getCourse(cid, requestComponent.getUid()));
    }
    //导师修改范围数和最大学生数
    @PatchMapping("settings")
    public Map patchSettings(@RequestBody Teacher t) {
        Teacher teacher = userService.updateTeacher(t.getMaxNum(), t.getMinRank(), requestComponent.getUid());
        return Map.of("teacher", teacher);
    }

    //删除课程
    @PostMapping("deleteCourse")
    public Map deleteCourse(@RequestBody Course c){
        teacherService.deleteCourse(c.getId());
        Teacher t = userService.getTeacher(requestComponent.getUid());
        return Map.of("tcourses",t.getCourses());

    }
   @PostMapping("students")//添加学生
    public Map postStudent(@RequestBody Student s) {
        Student student = courseService.addStudent(s, requestComponent.getUid());
        return Map.of("student", s);
    }
    //计算学生
    @GetMapping("excuteStudent")
    public Map excuteStudent(){
        Teacher t = userService.getTeacher(requestComponent.getUid());
        List<Integer> studentNumbers = teacherService.excuteStudent(t);
        int num = studentNumbers.size();
        User[] users = new User[num];
        for(int i=0;i<num;i++){
            users[i] = userService.getUser(studentNumbers.get(i));
        }
        log.debug("{}", users);
        return Map.of("studentUsers",users);
    }
    //修改方向
    @PostMapping("updateDirections")
    public Map updateDirections(@RequestBody List<Direction> newDirections){
        teacherService.updateDirections(newDirections);
        return Map.of("result",1);
    }

}
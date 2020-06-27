package com.example.springbootgraduationproject.controller.vo;

//在控制层和前端之间进行数据互交的对象

import com.example.springbootgraduationproject.entity.Course;
import com.example.springbootgraduationproject.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class CourseVO {//某一门课下的全部学生
    private Course course;
    private List<Student> students;
}
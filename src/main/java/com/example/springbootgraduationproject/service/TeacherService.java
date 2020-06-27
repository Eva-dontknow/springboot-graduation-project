package com.example.springbootgraduationproject.service;

import com.example.springbootgraduationproject.entity.Teacher;
import com.example.springbootgraduationproject.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }
}

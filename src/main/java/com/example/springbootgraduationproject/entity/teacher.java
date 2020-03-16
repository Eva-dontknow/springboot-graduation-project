package com.example.springbootgraduationproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.util.List;

@Entity
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private String name;
    //课程最低分
    private double LWeight;
    //课程最高分
    private double HWeight;
    //实际指导学生数
    private int actualNum;

    @OneToMany(mappedBy = "teacher")
    private List<Students> students;
}

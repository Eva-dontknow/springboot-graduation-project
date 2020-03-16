package com.example.springbootgraduationproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Entity
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;//密码是学号？
    private String name;

    @OneToMany(mappedBy = "students")
    private List<couesesAndStudents> couesesAndStudents;

    @ManyToOne
    private teacher teacher;
}

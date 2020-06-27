package com.example.springbootgraduationproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@JsonIgnoreProperties({"courses","students"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //组合User和teacher把User变为Teacher的一个部分
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @MapsId//共用主键
    private User user;
    private int maxNum;//老师最大可带的学生数
    private int minRank;//最低分名次
    private int actualNum; //实际指导学生数

    //和学生的一对多的关系
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Student> students;
    //和课程是一对多的关系
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Course> courses;
    //一个老师只能带一个方向的学生
    @OneToOne(mappedBy = "teacher")
    private Direction direction;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    public Teacher(Integer id) {
        this.id = id;
    }
}
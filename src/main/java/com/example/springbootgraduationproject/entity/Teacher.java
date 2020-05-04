package com.example.springbootgraduationproject.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Entity
@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int maxNum;//老师最大可带的学生数
    private int minRank;//最低分名次
    private int actualNum; //实际指导学生数

    //和学生的一对多的关系
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Student> students;
    //和课程是一对多的关系
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Course> Course;

}
/*老师
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @MapsId
    private User user;
    private Integer quantity;
    private Integer ranges;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;

    public Teacher(Integer id) {
        this.id = id;
    }
 */
/*
 // the maximum of students the teacher can choose
    @Max(value = 129)
    @PositiveOrZero
    private int maxStudentNumber;
    // the minimum ranking of student who choose the teacher
    @Max(value = 129)
    @PositiveOrZero
    private int minRanking;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Student> students;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.REMOVE)
    private List<Course> courses;
     */
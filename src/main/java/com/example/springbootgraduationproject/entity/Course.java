package com.example.springbootgraduationproject.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String  name;

    //和选课类的一对多关系
    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Elective> electives;

    //和老师的多对一关系
    @ManyToOne
    private Teacher teacher;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            "on update current_timestamp",
            updatable = false, insertable = false)
    private LocalDateTime insertTime;
}
/*
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float weight;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course")
    List<Student> students;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
 */

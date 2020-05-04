package com.example.springbootgraduationproject.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
/*import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;*/

@Entity
@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Elective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;//选课备注
    private double grade;//课程分数，需要对分数进行限制：0≤grade≤100
    private double weight;//课程权重

    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            "on update current_timestamp",
            updatable = false, insertable = false)
    private LocalDateTime insertTime;
}
/*
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @PositiveOrZero
    @Max(value = 100, message = "百分制成绩不能超过100分")
    private double grade;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
    //columnDefinition = "TIMESTAMP:映射过去为datatime，但是我们想让他映射为时间戳，所以做一个显示的声明
    //DEFAULT CURRENT_TIMESTAMP同时让它的时间戳默认为当前的时间戳
    //updatable = false,insertable = false,插入的时间和更新的时间在业务逻辑层都不允许更改
    //"on update current_timestamp":更新时间为当前时间戳，即更新后保存时间
    //JoinColumn(unique=true)唯一约束
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
}
 */
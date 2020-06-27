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
@AllArgsConstructor
@JsonIgnoreProperties({"students"})
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String detail;
    private String name;

    //和学生的一对多关系，一个研究方向可以有很多学生，但是一个学生的研究方向只有一个（研究方向太多学不过来）
    @OneToMany(mappedBy = "direction")
    private List<Student> students;

    @OneToOne//和老师是一对一的关系
    @JoinColumn(unique = true)
    private Teacher teacher;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
}

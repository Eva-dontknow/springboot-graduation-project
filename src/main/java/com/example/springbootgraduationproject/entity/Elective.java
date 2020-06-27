package com.example.springbootgraduationproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
/*import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;*/

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Elective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String detail;//选课备注
    private double grade;//课程分数，需要对分数进行限制：0≤grade≤100
    private double weight;//课程权重

    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
}

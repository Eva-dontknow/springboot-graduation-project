package com.example.springbootgraduationproject.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class couesesAndStudents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    @ManyToOne
    private Students students;
    @ManyToOne
    private courses courses;
    /*
@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"courses", "students"})
public class Teacher extends User {
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
}

     */
}

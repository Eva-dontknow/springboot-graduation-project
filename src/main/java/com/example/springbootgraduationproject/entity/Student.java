package com.example.springbootgraduationproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private int id;
    // @NotBlank(message = "姓名不能为空")//@NotBlank只能作用在String上
    //使用@NotBlank等注解时，一定要和@valid一起使用，不然@NotBlank不起作用
    private String name;
    private double weightedGrade;//当前加权

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)//和选课类的一对多关系
    private List<Elective> electives;

    @OneToMany(cascade = CascadeType.REMOVE)//和研究方向的一对多关系
    private List<Direction> directions;

    @ManyToOne //和老师的一对一关系
    private Teacher teacher;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            "on update current_timestamp",
            updatable = false, insertable = false)
    private LocalDateTime insertTime;
}
/*
 // 此属性为学生按照选择导师设置的计算规则计算出的加权成绩，不能直接进行设置
    // 是通过系统自动计算出来的
    private double weightedGrade;
    private int weightedRank;

    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Elective> electives;
    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Direction> directions;
 */
/*import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
// 继承时使用的建表策略，分别生成父表和子表
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    public enum Role {
        STUDENT, TEACHER, ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // this is the username needed to be input
    @Column(unique = true)
    @NotBlank(message = "登录名不能为空")
    @Size(min = 6, message = "学号长度必须大于等于")
    private String identityNo;

    @NotBlank(message = "姓名不能为空")
    private String name;

    // 返回对象时忽略密码属性
    @Size(min = 6, message = "用户密码长度必须不少于{min}")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 角色同样不需要返回给前端，因为前端会通过密钥对获取角色信息
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
}
 */


package com.example.springbootgraduationproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties({"electives"})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //组合User和teacher把User变为Teacher的一个部分
    @OneToOne(cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @MapsId//共用主键
    private User user;
    // @NotBlank(message = "姓名不能为空")//@NotBlank只能作用在String上
    //使用@NotBlank等注解时，一定要和@valid一起使用，不然@NotBlank不起作用
    private double weightedGrade;//当前加权
    private String mydirct;
    //对密码不进行序列化
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)//和选课类的一对多关系
    private List<Elective> electives;

    @ManyToOne(cascade = CascadeType.REMOVE)//和研究方向的多对一关系
    private Direction direction;

    @ManyToOne //和老师的多对一关系
    private Teacher teacher;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;

}


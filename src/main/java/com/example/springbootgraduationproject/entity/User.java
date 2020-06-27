package com.example.springbootgraduationproject.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User {
    /*
     和teacher、student之间变成单向的oneToOne关系。因为User类和
     其他两个类之间的关系只能是一对一，但是对于User类来说他的oneToOne
     是没有意义的，因为不知道是对Student还是teacher，所以干脆不写，
     变成单向的oneToOne关系
     推荐多用组合少用继承
     */
    public enum Role {
        STUDENT, TEACHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)
    private Integer number;
    /*
    密码不想序列化，输入的时候封装到user里，避免被彩虹表破解，
    所以序列化的时候不需要密码属性，而反序列化的时候需要密码属性，
    因为需要密码验证登录，当不想某个属性被序列化的时候，可以采用一些属性
    @JsonIgnore:序列化和反序列化全部忽略
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY),序列化时忽略
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
}
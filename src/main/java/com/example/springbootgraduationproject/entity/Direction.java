package com.example.springbootgraduationproject.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String detail;
    private String name;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            "on update current_timestamp",
            updatable = false, insertable = false)
    private LocalDateTime insertTime;
    @ManyToOne
    private Student student;
}

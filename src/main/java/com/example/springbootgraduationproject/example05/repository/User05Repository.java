package com.example.springbootgraduationproject.example05.repository;

import com.example.springbootgraduationproject.example05.entity.User05;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User05Repository extends JpaRepository<User05,Integer> {

}

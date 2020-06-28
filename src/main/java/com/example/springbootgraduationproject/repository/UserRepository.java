package com.example.springbootgraduationproject.repository;

import com.example.springbootgraduationproject.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User,Integer>{
    @Query("from User u where u.number=:num")
    User find(@Param("num") int num);
//或者
    User findById(int id);
}

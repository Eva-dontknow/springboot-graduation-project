package com.example.springbootgraduationproject.repository;

import com.example.springbootgraduationproject.entity.Course;
import com.example.springbootgraduationproject.entity.Elective;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElectiveRepository extends BaseRepository<Elective,Integer>{
    void deleteAllByCourse_Id(int id);
    List<Elective> findAllByCourse(Course course);

    @Query("SELECT sc FROM Elective sc WHERE sc.course.id=:cid")
    Optional<Elective> getSC(@Param("cid")int cid);

    @Query("SELECT sc FROM Elective sc WHERE sc.student.user.id=:sid")
    List<Elective> findStudent(@Param("sid")int sid);
}

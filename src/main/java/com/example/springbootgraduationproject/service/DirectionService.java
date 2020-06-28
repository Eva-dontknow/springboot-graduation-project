package com.example.springbootgraduationproject.service;

import com.example.springbootgraduationproject.entity.Direction;
import com.example.springbootgraduationproject.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectionService {
    @Autowired
    private DirectionRepository directionRepository;
    public void addDirection(Direction direction){
        directionRepository.save(direction);
    }
}

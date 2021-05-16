package com.example.demo.mapper;

import com.example.demo.model.Like;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeMap  extends JpaRepository<Like, Integer> {
}

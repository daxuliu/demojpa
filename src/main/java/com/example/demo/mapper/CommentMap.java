package com.example.demo.mapper;

import com.example.demo.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentMap extends JpaRepository<Comment, Integer> {
}

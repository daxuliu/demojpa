package com.example.demo.mapper;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMap extends JpaRepository<User, Integer> {

    }

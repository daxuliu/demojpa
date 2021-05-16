package com.example.demo.mapper;

import com.example.demo.model.Adoption;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdoptMap  extends JpaRepository<Adoption, Integer> {
}

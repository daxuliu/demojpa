package com.example.demo.mapper;

import com.example.demo.model.Adopter;
import com.example.demo.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopterMap extends JpaRepository<Adopter, Integer> {
}

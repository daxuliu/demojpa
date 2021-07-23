package com.example.demo.mapper;

import com.example.demo.model.Adopter;
import com.example.demo.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdopterMap extends JpaRepository<Adopter, Integer> {
    @Query("select  a from Adopter a where a.de like %?1%")
    public Adopter findAdopterByDeLike(String de);
}

package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    List<Genre> findByNameIgnoreCase(String name);
}

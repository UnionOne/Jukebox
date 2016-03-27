package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}

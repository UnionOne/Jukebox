package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}

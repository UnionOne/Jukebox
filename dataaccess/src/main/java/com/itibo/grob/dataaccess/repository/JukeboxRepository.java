package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Jukebox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JukeboxRepository extends JpaRepository<Jukebox, Integer> {
}

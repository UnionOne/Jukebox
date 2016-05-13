package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Jukebox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JukeboxRepository extends JpaRepository<Jukebox, Integer> {

}

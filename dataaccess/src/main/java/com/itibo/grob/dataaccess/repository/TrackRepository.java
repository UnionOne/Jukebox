package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Integer> {

}

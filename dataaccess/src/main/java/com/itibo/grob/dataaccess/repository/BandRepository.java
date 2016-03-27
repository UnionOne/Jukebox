package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends JpaRepository<Band, Integer> {
}

package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<Band, Integer> {

}

package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

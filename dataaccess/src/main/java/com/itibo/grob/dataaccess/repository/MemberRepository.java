package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findAllMemberByFirstName(String name);
}

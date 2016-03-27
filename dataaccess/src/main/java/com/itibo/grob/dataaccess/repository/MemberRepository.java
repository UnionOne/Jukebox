package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> findMemberByFirstName(String name);
}

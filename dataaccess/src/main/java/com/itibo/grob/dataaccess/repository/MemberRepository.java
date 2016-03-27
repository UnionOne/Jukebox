package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByFirstName(String name);
}

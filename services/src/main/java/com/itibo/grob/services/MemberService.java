package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Member;
import com.itibo.grob.services.common.GenericService;

import java.util.List;

public interface MemberService extends GenericService<Member, Integer> {
    List<Member> findAllMemberByFirstName(String name);
}

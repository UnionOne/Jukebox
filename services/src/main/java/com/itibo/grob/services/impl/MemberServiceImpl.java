package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.Member;
import com.itibo.grob.dataaccess.repository.MemberRepository;
import com.itibo.grob.services.MemberService;
import com.itibo.grob.services.common.AbstractGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberServiceImpl extends AbstractGenericService<Member, Integer, MemberRepository>
        implements MemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public List<Member> findAllMemberByFirstName(String name) {
        LOGGER.info("Finding {} entity with name = {}", simpleTypeName, name);
        System.out.println("Finding " + simpleTypeName + " entity with name = " + name);
        return repository.findAllMemberByFirstName(name);
    }
}

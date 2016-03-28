package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Member;
import com.itibo.grob.services.common.GenericServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class MemberServiceTest extends GenericServiceTest<Member, Integer, MemberService> {

    @Override
    protected Member generateEntity() {
        return entityUtils.generateMember();
    }

    @Override
    protected Iterable<? extends Member> generateEntities(int maxEntitiesCount) {
        List<Member> list = new LinkedList<>();

        for (int i = 0; i < maxEntitiesCount; i++) {
            list.add(generateEntity());
        }

        return list;
    }

    @Test
    public void findAllMemberByFirstNameTest() {
        Member saved = service.save(entity);
        Member found = service.findAllMemberByFirstName(entity.getFirstName()).get(0);

        Assert.assertEquals(saved, found);
    }
}

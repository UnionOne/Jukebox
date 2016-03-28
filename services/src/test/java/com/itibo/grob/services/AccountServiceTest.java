package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.services.common.GenericServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class AccountServiceTest extends GenericServiceTest<Account, Integer, AccountService> {
    @Override
    protected Account generateEntity() {
        return entityUtils.generateAccount();
    }

    @Override
    protected Iterable<? extends Account> generateEntities(int maxEntitiesCount) {
        List<Account> list = new LinkedList<>();

        for (int i = 0; i < maxEntitiesCount; i++) {
            list.add(generateEntity());
        }

        return list;
    }

    @Test
    public void findOneAccountByIdTest() {
        Account saved = service.save(entity);
        Account found = service.findOneAccountById(entity.getId());

        Assert.assertEquals(saved, found);
    }

    @Test
    public void findOneAccountByLoginTest() {
        Account saved = service.save(entity);
        Account found = service.findOneAccountByLogin(entity.getLogin());

        Assert.assertEquals(saved, found);
    }
}

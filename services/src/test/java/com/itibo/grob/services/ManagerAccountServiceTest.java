package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.services.common.AbstractSpringTest;
import com.itibo.grob.services.common.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class ManagerAccountServiceTest extends AbstractSpringTest{
    @Autowired
    private EntityUtils entityUtils;

    @Autowired
    private ManagerAccountService managerAccountService;

    private Account account;

    @Before
    public void before() {
        account = entityUtils.generateAccount();
    }

    @Test
    public void addAccountTest() {
        managerAccountService.addAccount(account);
    }
}

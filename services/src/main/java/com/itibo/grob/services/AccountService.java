package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.services.common.GenericService;

public interface AccountService extends GenericService<Account, Integer> {
    Account findOneAccountById(Integer id);

    Account findOneAccountByLogin(String login);

    Account deleteByLogin(String login);
}

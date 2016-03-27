package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.services.common.GenericService;

public interface AccountService extends GenericService<Account, Integer> {
    Account findById(Integer id);

    Account findByLogin(String login);
}

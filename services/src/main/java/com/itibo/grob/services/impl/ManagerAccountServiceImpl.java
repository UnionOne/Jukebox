package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Role;
import com.itibo.grob.services.AccountService;
import com.itibo.grob.services.ManagerAccountService;
import com.itibo.grob.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ManagerAccountServiceImpl implements ManagerAccountService {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Override
    public void addAccount(Account account) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        roleService.save(roles);

        account.setRoles(roles);
        accountService.save(account);
    }
}

package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findById(Integer id);

    Account findByLogin(String login);
}

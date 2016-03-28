package com.itibo.grob.dataaccess.repository;

import com.itibo.grob.dataaccess.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findOneAccountById(Integer id);

    Account findOneAccountByLogin(String login);

    @Query(value = "delete from account where login = ?1 RETURNING *", nativeQuery = true)
    Account deleteByLogin(String login);
}

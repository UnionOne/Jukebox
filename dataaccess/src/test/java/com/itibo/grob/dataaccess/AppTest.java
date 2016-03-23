package com.itibo.grob.dataaccess;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Role;
import com.itibo.grob.dataaccess.repository.AccountRepository;
import com.itibo.grob.dataaccess.repository.RoleRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring-db-context.xml")
@Transactional
public class AppTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    @Rollback(false)
    public void setUp() {
        Account ivan = new Account("Govnov", "email@mail.com", "pass", "Ivan", "Govnov", "jukebox ivana");
        ivan.setRole(new Role("ROLE_USER"));

        Account lenin = new Account("Ilich", "lenin@mail.com", "pass", "Volodia", "Lenin", "jukebox lenina");
        lenin.setRole(new Role("ROLE_ADMIN"));

        accountRepository.save(Arrays.asList(ivan, lenin));
    }

    @Test
    public void testCount() {
        System.out.println("********** COUNT **********");
        System.out.println("Account count: " + accountRepository.count());
        System.out.println("********** COUNT **********");
    }

    @Test
    public void testFindByLogin() {
        System.out.println("********** FIND BY LOGIN **********");
        System.out.println("Account by id: " + accountRepository.findByLogin("Govnov"));
        System.out.println("********** FIND BY ID **********");
    }

    @Test
    public void testFindById() {
        System.out.println("********** FIND BY ID **********");
        System.out.println("Account by id: " + accountRepository.findById(1));
        System.out.println("********** FIND BY ID **********");
    }
    @Test
    public void testAddAccount() {
        System.out.println("********** ADD ACCOUNT **********");
        Account newAccount = new Account("vladik", "damski@ugodnil.com", "esculentum512", "Vlad", "Sidliarevich", "vladika jukebox");
        newAccount.setRole(new Role("ROLE_USER"));
        accountRepository.save(newAccount);
        System.out.println("New Account by login: " + accountRepository.findByLogin("vladik"));
        System.out.println("********** ADD ACCOUNT **********");
    }

    @Test
    public void testRoleCount() {
        System.out.println("********** ROLES COUNT **********");
        System.out.println("Account count: " + roleRepository.count());
        System.out.println("********** ROLES COUNT **********");
    }

    @After
    public void clean() {
        accountRepository.deleteAll();
    }
}

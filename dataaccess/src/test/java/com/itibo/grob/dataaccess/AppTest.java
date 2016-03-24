package com.itibo.grob.dataaccess;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Role;
import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.dataaccess.repository.AccountRepository;
import com.itibo.grob.dataaccess.repository.RoleRepository;
import com.itibo.grob.dataaccess.repository.TrackRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring-db-context.xml")
@Transactional
public class AppTest {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TrackRepository trackRepository;

    @Before
    @Rollback(false)
    public void setUp() {
        Account ivan = new Account("Govnov", "email@mail.com", "pass", "Ivan", "Govnov", "jukebox ivana");

        List<Role> ivanRoleList = new ArrayList<>();
        ivanRoleList.add(new Role("ROLE_USER"));
        roleRepository.save(ivanRoleList);
        ivan.setRoles(ivanRoleList);

        Account lenin = new Account("Ilich", "lenin@mail.com", "pass", "Volodia", "Lenin", "jukebox lenina");

        List<Role> leninRoleList = new ArrayList<>();
        leninRoleList.add(new Role("ROLE_USER"));
        leninRoleList.add(new Role("ROLE_ADMIN"));
        roleRepository.save(leninRoleList);
        lenin.setRoles(leninRoleList);

        accountRepository.save(Arrays.asList(ivan, lenin));
    }

    @Test
    public void testCount() {
        System.out.println("\n********** COUNT **********");
        System.out.println("Account count: " + accountRepository.count());
        System.out.println("********** COUNT **********");
    }

    @Test
    public void testFindByLogin() {
        System.out.println("\n********** FIND BY LOGIN **********");
        System.out.println("Account by id: " + accountRepository.findByLogin("Ilich"));
        System.out.println("********** FIND BY ID **********");
    }

    @Test
    public void testFindById() {
        System.out.println("\n********** FIND BY ID **********");
        System.out.println("Account by id: " + accountRepository.findById(1));
        System.out.println("********** FIND BY ID **********");
    }

    @Test
    public void testAddAccount() {
        System.out.println("\n********** ADD ACCOUNT **********");
        Account newAccount = new Account("vladik", "damski@ugodnil.com", "esculentum512", "Vlad", "Sidliarevich", "vladika jukebox");

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROlE_USER"));
        roleRepository.save(roles);
        newAccount.setRoles(roles);

        accountRepository.save(newAccount);
        System.out.println("New Account by login: " + accountRepository.findByLogin("vladik"));
        System.out.println("********** ADD ACCOUNT **********");
    }

    @Test
    public void testFindAllAccount() {
        System.out.println("\n********** FIND ALL **********");
        System.out.println("Account by id: " + accountRepository.findAll());
        System.out.println("********** FIND ALL **********");
    }

    @Test
    public void testRolesCount() {
        System.out.println("\n********** ROLES COUNT **********");
        System.out.println("Roles count: " + roleRepository.count());
        System.out.println("********** ROLES COUNT **********");
    }

    @Test
    public void testAddTrack() {
        System.out.println("\n********** ADD TRACK **********");
        Track track = new Track("GO", "", "", "" ,"", "");
        System.out.println("Track added: " + trackRepository.save(track));
        System.out.println("********** ADD TRACK **********");
    }

    @After
    public void clean() {
        accountRepository.deleteAll();
    }
}

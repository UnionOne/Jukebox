package com.itibo.grob.dataaccess;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Jukebox;
import com.itibo.grob.dataaccess.model.Role;
import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.dataaccess.repository.AccountRepository;
import com.itibo.grob.dataaccess.repository.JukeboxRepository;
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
import java.util.LinkedList;
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

    @Autowired
    private JukeboxRepository jukeboxRepository;


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

        Jukebox jukebox = new Jukebox();

        List<Track> tracks = new LinkedList<>();
        tracks.add(new Track("GrOb - Ivan Govnov", "03:32", "punk", "Vse idet po plany", "GrOb", "link"));
        tracks.add(new Track("Iron Maiden - Prowler", "03:54", "heavy metal", "Iron Maiden", "Iron Maiden", "link"));
        trackRepository.save(tracks);
        jukebox.setTracks(tracks);

        jukeboxRepository.save(jukebox);
    }

    @Test
    public void testCount() {
        System.out.println("\n********** ACCOUNTS COUNT **********");
        System.out.println("Account count: " + accountRepository.count());
        System.out.println("********** ACCOUNTS COUNT **********");
    }

    @Test
    public void testFindAccountByLogin() {
        System.out.println("\n********** FIND ACCOUNT BY LOGIN **********");
        System.out.println("Account by id: " + accountRepository.findByLogin("Ilich"));
        System.out.println("********** FIND ACCOUNT BY ID **********");
    }

    @Test
    public void testFindAccountById() {
        System.out.println("\n********** FIND ACCOUNT BY ID **********");
        System.out.println("Account by id: " + accountRepository.findById(1));
        System.out.println("********** FIND ACCOUNT BY ID **********");
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
        System.out.println("\n********** FIND ALL ACCOUNTS **********");
        System.out.println("Accounts: " + accountRepository.findAll());
        System.out.println("********** FIND ALL ACCOUNTS **********");
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
        Track track = new Track("GrOb - Ivan Govnov", "03:32", "punk", "Vse idet po plany" ,"GrOb", "link");
        System.out.println("Track added: " + trackRepository.save(track));
        System.out.println("********** ADD TRACK **********");
    }

    @Test
    public void testFindAllJukebox() {
        System.out.println("\n********** ALL JUKEBOXES **********");
        System.out.println("Jukeboxes: " + jukeboxRepository.findAll());
        System.out.println("********** ALL JUKEBOXES **********");
    }

    @Test
    public void testCountTrack() {
        System.out.println("\n********** TRACKS COUNT **********");
        System.out.println("Tracks count: " + trackRepository.count());
        System.out.println("********** TRACKS COUNT **********");
    }

    @After
    public void clean() {
        accountRepository.deleteAll();
    }
}

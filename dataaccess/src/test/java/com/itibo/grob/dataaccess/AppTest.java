package com.itibo.grob.dataaccess;

import com.itibo.grob.dataaccess.model.*;
import com.itibo.grob.dataaccess.repository.*;
import org.junit.After;
import org.junit.Assert;
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

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Before
    @Rollback(false)
    public void setUp() {
        Account ivan = new Account("Govnov", "email@mail.com", "pass", "Ivan", "Govnov");

        List<Role> ivanRoleList = new ArrayList<>();
        ivanRoleList.add(new Role("ROLE_USER"));
        roleRepository.save(ivanRoleList);
        ivan.setRoles(ivanRoleList);

        Jukebox ivansJukebox = new Jukebox();
        List<Track> ivansTracks = new LinkedList<>();

        Genre psyGenre = new Genre("psychedelic rock");
        genreRepository.save(psyGenre);
        ivansTracks.add(new Track("Jefferson Airplane - White Rabbit", "03:32", psyGenre, "Surrealistic Pillow", "Jefferson Airplane", "link"));

        Genre rockGenre = new Genre("rock'n'roll");
        genreRepository.save(rockGenre);
        ivansTracks.add(new Track("Animals - House Of The Rising Sun", "03:15", rockGenre, "The Animals", "Animals", "link"));

        trackRepository.save(ivansTracks);
        ivansJukebox.setTracks(ivansTracks);

        jukeboxRepository.save(ivansJukebox);
        ivan.setJukebox(ivansJukebox);

        Account lenin = new Account("Ilich", "lenin@mail.com", "pass", "Volodia", "Lenin");

        List<Role> leninRoleList = new ArrayList<>();
        leninRoleList.add(new Role("ROLE_USER"));
        leninRoleList.add(new Role("ROLE_ADMIN"));
        roleRepository.save(leninRoleList);
        lenin.setRoles(leninRoleList);

        Jukebox leninJukebox = new Jukebox();

        List<Track> leninTracks = new LinkedList<>();

        Genre speedGenre = new Genre("speed metal");
        genreRepository.save(speedGenre);
        leninTracks.add(new Track("Megadeth - She Wolf", "03:00", speedGenre, "Cryptic Writings", "Megadeth", "link"));

        Genre speedGenre2 = new Genre("speed metal");
        genreRepository.save(speedGenre2);
        leninTracks.add(new Track("Megadeth - Hangar 18", "05:11 ", speedGenre2, "Rust in Peace", "Megadeth", "link"));

        trackRepository.save(leninTracks);
        leninJukebox.setTracks(leninTracks);

        jukeboxRepository.save(leninJukebox);
        lenin.setJukebox(leninJukebox);

        accountRepository.save(Arrays.asList(ivan, lenin));

        Album album = new Album("Cryptic Writings", "1997", "Megadeth");
        Album album1 = new Album("Rust in Peace", "1990", "Megadeth");
        albumRepository.save(Arrays.asList(album, album1));

        Member david = new Member("David", "Scott Mustaine");
        david.setBiography("Da da eto ego biografia");
        memberRepository.save(david);

        Member david2 = new Member("David", "Warren Ellefson");
        david2.setBiography("Da da eto toje ego biografia");
        memberRepository.save(david2);

        List<Member> members = new LinkedList<>();
        members.add(david);
        members.add(david2);

        List<Album> albums = new LinkedList<>();
        albums.add(album);
        albums.add(album1);

        Band megadeth = new Band("Megadeth", "1983");
        megadeth.setMembers(members);
        megadeth.setAlbums(albums);

        bandRepository.save(megadeth);
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
        Account newAccount = new Account("vladik", "damski@ugodnil.com", "esculentum512", "Vlad", "Sidliarevich");

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        roleRepository.save(roles);
        newAccount.setRoles(roles);

        Jukebox jukebox = new Jukebox();

        List<Track> tracks = new LinkedList<>();

        Genre punkGenre = new Genre("punk");
        genreRepository.save(punkGenre);
        tracks.add(new Track("GrOb - Ivan Govnov", "03:32", punkGenre, "Vse idet po plany", "GrOb", "link"));

        Genre heavyGenre = new Genre("heavy metal");
        genreRepository.save(heavyGenre);
        tracks.add(new Track("Iron Maiden - Prowler", "03:54", heavyGenre, "Iron Maiden", "Iron Maiden", "link"));

        trackRepository.save(tracks);
        jukebox.setTracks(tracks);

        jukeboxRepository.save(jukebox);
        newAccount.setJukebox(jukebox);

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
    public void testFindJukeboxByAccountLogin() {
        System.out.println("\n********** Jukebox by Account login **********");
        System.out.println("Jukebox: " + accountRepository.findByLogin("Govnov").getJukebox());
        System.out.println("********** Jukebox by Account login **********");
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

        Genre punkGenre = new Genre("punk");
        genreRepository.save(punkGenre);
        Track track = new Track("GrOb - Ivan Govnov", "03:32", punkGenre, "Vse idet po plany", "GrOb", "link");

        System.out.println("Track added: " + trackRepository.save(track));
        System.out.println("********** ADD TRACK **********");
    }

    @Test
    public void testCountTrack() {
        System.out.println("\n********** TRACKS COUNT **********");
        System.out.println("Tracks count: " + trackRepository.count());
        System.out.println("********** TRACKS COUNT **********");
    }

    @Test
    public void testFindAllJukeboxes() {
        System.out.println("\n********** ALL JUKEBOXES **********");
        System.out.println("Jukeboxes: " + jukeboxRepository.findAll());
        System.out.println("********** ALL JUKEBOXES **********");
    }

    @Test
    public void testFindJukeboxesCount() {
        System.out.println("\n********** JUKEBOXES COUNT **********");
        System.out.println("Jukeboxes: " + jukeboxRepository.count());
        System.out.println("********** JUKEBOXES COUNT **********");
    }

    @Test
    public void testGenresCount() {
        System.out.println("\n********** GENRES COUNT **********");
        System.out.println("Genres: " + genreRepository.count());
        Assert.assertEquals(4, genreRepository.count());
        System.out.println("********** GENRES COUNT **********");
    }

    @Test
    public void testFindGenresByNameIgnoreCase() {
        System.out.println("\n********** GENRES BY NAME **********");
        List<Genre> genres = genreRepository.findByNameIgnoreCase("speed metal");
        System.out.println("Genres: " + genres.toString());
        Assert.assertEquals(2, genres.size());
        System.out.println("********** GENRES BY NAME **********");
    }

    @Test
    public void testAlbumsCount() {
        System.out.println("\n********** ALBUMS COUNT **********");
        System.out.println("Albums: " + albumRepository.count());
        Assert.assertEquals(2, albumRepository.count());
        System.out.println("********** ALBUMS COUNT **********");
    }

    @Test
    public void testFindAllAlbums() {
        System.out.println("\n********** ALL ALBUMS **********");
        System.out.println("Albums: " + albumRepository.findAll());
        System.out.println("********** ALL ALBUMS **********");
    }

    @Test
    public void testFindAllBands() {
        System.out.println("\n********** ALL BANDS **********");
        System.out.println("Bands: " + bandRepository.findAll());
        System.out.println("********** ALL BANDS **********");
    }

    @Test
    public void testBandsCount() {
        System.out.println("\n********** BANDS COUNT **********");
        long count = bandRepository.count();
        System.out.println("Bands count: " + count);
        Assert.assertEquals(1, count);
        System.out.println("********** BANDS COUNT **********");
    }

    @Test
    public void testFindAllMembers() {
        System.out.println("\n********** ALL MEMBERS **********");
        System.out.println("Members: " + memberRepository.findAll());
        System.out.println("********** ALL MEMBERS **********");
    }

    @Test
    public void testMembersCount() {
        System.out.println("\n********** MEMBERS COUNT **********");
        long count = memberRepository.count();
        System.out.println("Members count: " + count);
        Assert.assertEquals(2, count);
        System.out.println("********** MEMBERS COUNT **********");
    }

    @Test
    public void testFindMemberByFirstName() {
        System.out.println("\n********** MEMBER BY FIRST NAME **********");
        System.out.println("Member by name: " + memberRepository.findMemberByFirstName("David"));
        System.out.println("********** MEMBER BY FIRST NAME**********");
    }

    @After
    public void clean() {
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        trackRepository.deleteAll();
        jukeboxRepository.deleteAll();
        genreRepository.deleteAll();
    }
}

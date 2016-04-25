package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.*;
import com.itibo.grob.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ManagerAccountServiceImpl implements ManagerAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    JukeboxService jukeboxService;

    @Autowired
    TrackService trackService;

    @Override
    public void addAccount(Account account) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
//        roles.add(new Role("ROLE_ADMIN"));

        Genre genre = new Genre("default");
        List<Track> tracks = new LinkedList<>();
        tracks.add(new Track("default", "default", genre, "default", "default", "default"));

        Jukebox jukebox = new Jukebox();
        jukeboxService.save(jukebox);
        jukebox.setTracks(tracks);

        account.setRoles(roles);
        account.setJukebox(jukebox);
        accountService.save(account);

        LOGGER.info("Setting role and jukebox to {} entity", account);
    }

    @Override
    public void addTrack(Account account, Track track) {
        Jukebox jukebox = account.getJukebox();
        List<Track> tracks = new LinkedList<>();

        if (jukebox.getTracks().size() != 0) {
            tracks = jukebox.getTracks();
        } else {
            Genre genre = new Genre("default");
            tracks.add(new Track("default", "default", genre, "default", "default", "default"));
        }

        tracks.add(track);

        jukebox.setTracks(tracks);

        account.setJukebox(jukebox);
        accountService.save(account);

        LOGGER.info("Add track to {} entity", account);
    }

    @Override
    public void deleteAccount(Account account) {
        List<Role> roleList = account.getRoles();

        Jukebox jukebox = account.getJukebox();

        List<Track> trackList = jukebox.getTracks();

        List<String> linkList = new ArrayList<>();
        for (Track track : trackList) {
            linkList.add(track.getLink());
        }

        for (String link : linkList) {
            File file = new File("/home/union" + link);
            if (file.delete()) {
                System.out.println("/home/union" + link + "file deleted");
            } else {
                System.out.println("No such file" + "/home/union" + link);
            }
        }
        linkList.clear();

        accountService.deleteByLogin(account.getLogin());

        jukeboxService.delete(jukebox);

        roleService.delete(roleList);

        LOGGER.info("Delete {} entity", account);
    }
}

package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.*;
import com.itibo.grob.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ManagerAccountServiceImpl implements ManagerAccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Override
    public void addAccount(Account account) {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
//        roles.add(new Role("ROLE_ADMIN"));

        Genre genre = new Genre("default");
        List<Track> tracks = new LinkedList<>();
        tracks.add(new Track("default", "default", genre, "default", "default", "default"));

        Jukebox jukebox = new Jukebox();
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

        if(jukebox.getTracks().size() != 0) {
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
}

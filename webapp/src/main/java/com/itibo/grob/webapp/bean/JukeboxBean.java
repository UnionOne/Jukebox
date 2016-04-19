package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Genre;
import com.itibo.grob.dataaccess.model.Jukebox;
import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.util.List;

@ManagedBean(name = "jukeboxBean")
@SessionScoped
@Component
public class JukeboxBean {
    @Autowired
    private AccountService accountService;

    @Autowired
    ManagerAccountService managerAccountService;

    @Autowired
    private JukeboxService jukeboxService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private TrackService trackService;

    private String name;
    private String duration;
    private String genreName;
    private String album;
    private String band;
    private String link;
    private String play;

    public void addTrack() {
        Genre genre = new Genre(genreName);
        Track track = new Track(name, duration, genre, album, band, link);

        managerAccountService.addTrack(getAccount(), track);
    }

    public void deleteTrack(Track track) {
        trackService.delete(track);

        File file = new File("/home/union" + track.getLink());
        if (file.delete()) {
            System.out.println("/home/union" + track.getLink() + "file deleted");
        } else System.out.println("No such file" + "/home/union" + track.getLink());
    }

    public void editTrack(Track track) {
        track.setEdit(true);
        this.name = track.getName();
        this.duration = track.getDuration();
        this.genreName = track.getGenreName();
        this.album = track.getAlbum();
        this.band = track.getBand();
        this.link = track.getLink();
        trackService.save(track);
    }

    public void saveTrack(Track track) {
        track.setEdit(false);
        track.setName(this.name);
        track.setDuration(this.duration);
        track.setGenre(new Genre(this.genreName));
        track.setAlbum(this.album);
        track.setBand(this.band);
        track.setLink(this.link);
        trackService.save(track);
    }

    private Account getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return accountService.findOneAccountByLogin(authentication.getName());
        }
        return null;
    }

    public List<Track> trackList() {
//        Track track = new Track();
        return getAccount().getJukebox().getTracks();
    }

    public void reset() {
        name = "";
        duration = "";
        genreName = "";
        album = "";
        band = "";
        link = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }
}

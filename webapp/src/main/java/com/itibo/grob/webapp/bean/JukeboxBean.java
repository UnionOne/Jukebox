package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Genre;
import com.itibo.grob.dataaccess.model.Jukebox;
import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.services.AccountService;
import com.itibo.grob.services.GenreService;
import com.itibo.grob.services.JukeboxService;
import com.itibo.grob.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "jukeboxBean")
@SessionScoped
@Component
public class JukeboxBean {
    @Autowired
    private AccountService accountService;

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

    public void addTrack() {
        Account account = getAccount();

        Genre genre = new Genre(genreName);
        genreService.save(genre);

        assert account != null;
        List<Track> tracks = account.getJukebox().getTracks();
        tracks.add(new Track(name, duration, genre, album, band, link));
        trackService.save(tracks);

        accountService.save(account);
    }

    private Account getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return accountService.findOneAccountByLogin(authentication.getName());
        }
        return null;
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
}

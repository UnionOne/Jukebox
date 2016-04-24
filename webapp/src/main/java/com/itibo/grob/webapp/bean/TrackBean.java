package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Album;
import com.itibo.grob.dataaccess.model.Genre;
import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.services.AlbumService;
import com.itibo.grob.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "trackBean")
@SessionScoped
@Component
public class TrackBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    TrackService trackService;

    @Autowired
    AlbumService albumService;

    private String name;
    private String duration;
    private String genre;
    private String album;
    private String band;
    private String link;
    private Album currentAlbum;
    private List<Track> trackList;

    public TrackBean() {
    }

    public void addTrack() {
        Genre genre = new Genre("unknown");
        Track track = new Track("unknown", "unknown", genre, currentAlbum.getName(), currentAlbum.getBand(), "unknown");
//        track.setEdit(true);
        List<Track> list = currentAlbum.getTracks();
        list.add(track);
        currentAlbum.setTracks(list);
        albumService.save(currentAlbum);
    }

    public void editTrack(Track track) {
        track.setEdit(true);
        this.name = track.getName();
        this.duration = track.getDuration();
        this.genre = track.getGenreName();
        this.album = track.getAlbum();
        this.band = track.getBand();
        trackService.save(track);
    }

    public void saveTrack(Track track) {
        track.setEdit(false);
        track.setName(this.name);
        track.setDuration(this.duration);
        track.setGenre(new Genre(this.genre));
        track.setAlbum(this.album);
        track.setBand(this.band);
        trackService.save(track);
    }

    public void deleteTrack(Track track) {
        trackService.delete(track);
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    public Album getCurrentAlbum() {
        return currentAlbum;
    }

    public void setCurrentAlbum(Album currentAlbum) {
        this.currentAlbum = currentAlbum;
    }

    public List<Track> getTrackList() {
        this.trackList = currentAlbum.getTracks();
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}

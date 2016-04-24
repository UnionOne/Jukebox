package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Album;
import com.itibo.grob.dataaccess.model.Band;
import com.itibo.grob.services.AlbumService;
import com.itibo.grob.services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "albumBean")
@SessionScoped
@Component
public class AlbumBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private BandService bandService;

    private String name;
    private String year;
    private String band;
    private String description;
    private List<Album> albumList;
    private Band currentBand;
    private Album currentAlbum;

    public AlbumBean() {
    }

    public void addAlbum() {
        Album album = new Album("unknown", "unknown", this.currentBand.getName());
        album.setEdit(true);
        List<Album> albumList = currentBand.getAlbums();
        albumList.add(album);
        currentBand.setAlbums(albumList);
        bandService.save(currentBand);
    }

    public void editAlbum(Album album) {
        album.setEdit(true);
        this.name = album.getName();
        this.year = album.getYear();
        this.band = album.getBand();
        albumService.save(album);
    }

    public void saveAlbum(Album album) {
        album.setEdit(false);
        album.setName(this.name);
        album.setYear(this.year);
        album.setBand(this.band);
        albumService.save(album);
    }

    public void editDescription() {
        currentAlbum.setEdit(true);
        this.description = currentAlbum.getDescription();
        albumService.save(currentAlbum);
    }

    public void saveDescription() {
        currentAlbum.setEdit(false);
        currentAlbum.setDescription(this.description);
        albumService.save(currentAlbum);
    }

    public void deleteAlbum(Album album) {
        albumService.delete(album);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public List<Album> getAlbumList() {
        this.albumList = currentBand.getAlbums();
        return albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public Band getCurrentBand() {
        return currentBand;
    }

    public void setCurrentBand(Band currentBand) {
        this.currentBand = currentBand;
    }

    public Album getCurrentAlbum() {
        return currentAlbum;
    }

    public void setCurrentAlbum(Album currentAlbum) {
        this.currentAlbum = currentAlbum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

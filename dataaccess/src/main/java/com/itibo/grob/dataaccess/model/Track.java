package com.itibo.grob.dataaccess.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "track")
public class Track implements Persistable<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "track_gen")
    @SequenceGenerator(name = "track_gen", sequenceName = "track_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "duration", nullable = false, length = 50)
    private String duration;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "album", nullable = false)
    private String album;

    @Column(name = "band", nullable = false)
    private String band;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "edit", nullable = false)
    private Boolean edit;

    public Track() {
        super();
    }

    public Track(String name, String duration, Genre genre, String album, String band, String link) {
        super();
        this.name = name;
        this.duration = duration;
        this.genre = genre;
        this.album = album;
        this.band = band;
        this.link = link;
        this.edit = false;
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Genre getGenre() {
        return this.genre;
    }

    public String getGenreName() {
        return this.genre.getName();
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", genre=" + genre +
                ", album='" + album + '\'' +
                ", band='" + band + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}

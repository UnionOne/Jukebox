package com.itibo.grob.dataaccess.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "album")
public class Album implements Persistable<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_gen")
    @SequenceGenerator(name = "album_gen", sequenceName = "album_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year")
    private String year;

    @Column(name = "band")
    private String band;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "album_track", schema = "public",
            joinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "track_id", referencedColumnName = "id")}
    )
    private List<Track> tracks;

    @Column(name = "description")
    private String description;

    @Column(name = "edit", nullable = false)
    private Boolean edit;

    public Album() {
        super();
    }

    public Album(String name, String year, String band) {
        super();
        this.name = name;
        this.year = year;
        this.band = band;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", band='" + band + '\'' +
                ", tracks=" + tracks +
                ", description='" + description + '\'' +
                '}';
    }
}

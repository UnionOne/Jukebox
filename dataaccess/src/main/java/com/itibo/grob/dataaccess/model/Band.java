package com.itibo.grob.dataaccess.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "band",
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_band_name", columnNames = {"name"})
        }
)
public class Band implements Persistable<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "band_gen")
    @SequenceGenerator(name = "band_gen", sequenceName = "band_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "band_member", schema = "public",
            joinColumns = {@JoinColumn(name = "band_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "id")}
    )
    private List<Member> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "band_album", schema = "public",
            joinColumns = {@JoinColumn(name = "band_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")}
    )
    private List<Album> albums;

    @Column(name = "year")
    private String year;

    @Column(name = "description")
    private String description;

    @Column(name = "edit", nullable = false)
    private Boolean edit;

    public Band() {
        super();
    }

    public Band(String name, String year) {
        super();
        this.name = name;
        this.year = year;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", albums=" + albums +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

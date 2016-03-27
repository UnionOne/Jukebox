package com.itibo.grob.dataaccess.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "genre")
public class Genre implements Persistable<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_gen")
    @SequenceGenerator(name = "genre_gen", sequenceName = "genre_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public Genre() {
        super();
    }

    public Genre(String name) {
        super();
        this.name = name;
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

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

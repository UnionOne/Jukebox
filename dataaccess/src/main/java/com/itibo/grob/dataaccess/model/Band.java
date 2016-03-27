package com.itibo.grob.dataaccess.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;

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

    //private String members;
    //private String albums;

    @Column(name = "year")
    private String year;

    @Column(name = "description")
    private String description;

    public Band() {
        super();
    }

    public Band(String name, String year) {
        super();
        this.name = name;
        this.year = year;
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

    @Override
    public String toString() {
        return "Band{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

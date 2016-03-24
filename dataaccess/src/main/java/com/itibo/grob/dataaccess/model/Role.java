package com.itibo.grob.dataaccess.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role implements Persistable<Integer> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    @SequenceGenerator(name = "role_gen", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Role() {
        super();
    }

    public Role(String name) {
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
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

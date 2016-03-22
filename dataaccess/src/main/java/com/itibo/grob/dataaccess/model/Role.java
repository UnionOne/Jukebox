package com.itibo.grob.dataaccess.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role implements Persistable<Integer> {
    private Integer id;
    private String name;
    private Set<Account> accounts = new HashSet<>(0);

    public Role() {
        super();
    }

    public Role(Integer id) {
        super();
        this.id = id;
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Role(String name, Integer id) {
        super();
        this.name = name;
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    @SequenceGenerator(name = "role_gen", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_role", schema = "public", joinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "account_id", nullable = false, updatable = false)})
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }
}

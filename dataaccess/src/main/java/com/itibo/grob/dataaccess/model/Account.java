package com.itibo.grob.dataaccess.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "account",
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_account_login", columnNames = {"login"}),
                @UniqueConstraint(name = "uc_account_email", columnNames = {"email"})
        }
)
public class Account implements Persistable<Integer> {
    private Integer id;
    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles = new HashSet<>(0);
    private String jukebox;

    public Account() {
        super();
    }

    public Account(Integer id) {
        super();
        this.id = id;
    }

    public Account(String login, String email, String password, String firstName, String lastName) {
        super();
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Account(Integer id, String login, String email, String password, String firstName, String lastName) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_gen")
    @SequenceGenerator(name = "account_gen", sequenceName = "account_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "login", nullable = false, length = 20)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_role", schema = "public", joinColumns = {
            @JoinColumn(name = "account_id", nullable = false, updatable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false)})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "jukebox")
    public String getJukebox() {
        return jukebox;
    }

    public void setJukebox(String jukebox) {
        this.jukebox = jukebox;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", roles=" + roles +
                ", jukebox=" + jukebox +
                '}';
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }
}

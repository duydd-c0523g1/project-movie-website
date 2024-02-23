package com.example.movie_ticket.model;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_role")
    private String nameRole;
    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<Account> accounts;
    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}

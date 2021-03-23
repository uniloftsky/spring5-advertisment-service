package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    public User() {}

    public User(String username, String password, String email, String name, String description, String website, String officeLocation) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.description = description;
        this.website = website;
        this.officeLocation = officeLocation;
    }

    private String username;
    private String password;
    private String email;
    private String name;
    private String description;
    private String website;
    private String officeLocation;
    private String img;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Advertisement> items = new HashSet<>();

}

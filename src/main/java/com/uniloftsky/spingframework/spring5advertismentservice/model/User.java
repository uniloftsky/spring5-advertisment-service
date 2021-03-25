package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseEntity {

    public User() {}

    public User(String username, String password, String email, String name, String description, String website, String officeLocation, String img) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.description = description;
        this.website = website;
        this.officeLocation = officeLocation;
        this.img = img;
    }

    @NotBlank(message = "{user.field.NotBlank}")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "{user.field.NotBlank}")
    private String password;

    @NotBlank(message = "{user.field.NotBlank}")
    private String email;

    @NotBlank(message = "{user.field.NotBlank}")
    private String name;

    @Size(max = 1000, message = "{user.description.Size}")
    @NotBlank(message = "{user.field.NotBlank}")
    private String description;

    @NotBlank(message = "{user.field.NotBlank}")
    private String website;

    @NotBlank(message = "{user.field.NotBlank}")
    private String officeLocation;

    private String img;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Advertisement> items = new HashSet<>();

}

package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Region extends BaseEntity {

    public Region(String name) {
        this.name = name;
    }

    @NotBlank(message = "{user.field.NotBlank}")
    private String name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<City> cities = new HashSet<>();

}

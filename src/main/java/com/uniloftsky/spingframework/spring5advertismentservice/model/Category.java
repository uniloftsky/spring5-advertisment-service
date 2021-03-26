package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends BaseEntity {

    public Category(String name) {
        this.name = name;
    }

    @NotBlank(message = "{user.field.NotBlank}")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Advertisement> ads = new HashSet<>();

}

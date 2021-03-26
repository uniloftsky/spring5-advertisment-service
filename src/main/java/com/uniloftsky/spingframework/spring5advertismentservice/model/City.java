package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City extends BaseEntity {

    @NotBlank(message = "{user.field.NotBlank}")
    private String name;

    @NotNull(message = "{user.field.NotBlank}")
    @ManyToOne
    private Region region;

}

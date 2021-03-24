package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class City extends BaseEntity {

    private String name;

    @ManyToOne
    private Region region;

}

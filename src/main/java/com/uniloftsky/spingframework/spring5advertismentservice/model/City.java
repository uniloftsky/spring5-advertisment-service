package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class City extends BaseEntity {

    private String name;

    @ManyToOne
    private Region region;

}

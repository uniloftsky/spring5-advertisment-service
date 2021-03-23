package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Advertisement extends BaseEntity {

    private String name;

    @ManyToOne
    private City city;

    @ManyToOne
    private Category category;

    private String description;
    private String responsibilities;
    private String qualifications;
    private String offer;
    private LocalDate publicDate;
    private String salary;
    private String jobNatural;
    private Status status;
    private String img;

    @ManyToOne
    private User user;


}

package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Status status = Status.CHECK;

    @ManyToOne
    private User user;


}

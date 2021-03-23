package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Category extends BaseEntity {

    private String name;

}

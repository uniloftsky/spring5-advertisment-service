package com.uniloftsky.spingframework.spring5advertismentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Advertisement extends BaseEntity {

    @NotBlank(message = "{user.field.NotBlank}")
    private String name;

    @NotNull(message = "{user.field.NotBlank}")
    @ManyToOne
    private City city;

    @NotNull(message = "{user.field.NotBlank}")
    @ManyToOne
    private Category category;

    @Size(max = 1000, message = "{ad.field.Size}")
    @NotBlank(message = "{user.field.NotBlank}")
    private String description;

    private String responsibilities;
    private String qualifications;
    private String offer;
    private LocalDate publicDate;
    private BigDecimal salary;
    private String jobNatural;

    @Column(name = "ad_status")
    private Status status = Status.CHECK_AD;

    @ManyToOne
    private User user;

}

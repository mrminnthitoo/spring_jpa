package com.minnthitoo.spring_jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.io.Serial;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class Human extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    // Name name;
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Formula(value = "CONCAT(first_name, \" \", last_name)")
    private String fullName;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column
    private Date birthday;


}

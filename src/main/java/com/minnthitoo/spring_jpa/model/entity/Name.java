package com.minnthitoo.spring_jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Formula;

@Data
@ToString
@Embeddable
public class Name {
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Formula(value = "CONCAT(first_name, \" \", last_name)")
    private String fullName;
}

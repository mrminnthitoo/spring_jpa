package com.minnthitoo.spring_jpa.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.ToString;

import java.io.Serial;

@ToString
@Data
@Entity
public class Movie extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column
    private String title;

    @Column(nullable = false)
    private int year;

    @Column
    private String genre;

}

package com.minnthitoo.spring_jpa.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity
public class Movie extends BaseEntity {

    @Column
    private String title;

    @Column
    private Long year;

    @Column
    private String genre;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "movie"
    )
    private MovieDetails movieDetails;

}

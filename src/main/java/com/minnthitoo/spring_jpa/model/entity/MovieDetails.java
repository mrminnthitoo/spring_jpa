package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class MovieDetails extends BaseEntity{

    @Column
    private String details;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "movie_id")
    private Movie movie;

}

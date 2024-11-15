package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class MovieDetails extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String details;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "movie_id")
    private Movie movie;
}

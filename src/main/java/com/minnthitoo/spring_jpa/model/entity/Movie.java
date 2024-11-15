package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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

//    @JsonIgnore
    @ToString.Exclude
    @OneToOne(optional = false,
            mappedBy = "movie",
            cascade = CascadeType.ALL)
    private MovieDetails movieDetails;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            }
    )
    @JoinTable(name = "actor_in_movie",
    joinColumns = {@JoinColumn(name = "movie_id")}, inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private Set<Actor> actors = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "movie_id")
    List<Comment> comments = new ArrayList<>();

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(name = "director_in_movie",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "director_id")}
    )
    private Set<Director> directors = new HashSet<>();

}

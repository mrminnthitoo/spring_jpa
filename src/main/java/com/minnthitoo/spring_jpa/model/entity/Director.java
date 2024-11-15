package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Director extends Human{

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "directors"
    )
    Set<Movie> movies = new HashSet<>();

}

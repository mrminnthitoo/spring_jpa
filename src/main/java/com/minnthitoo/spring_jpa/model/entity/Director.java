package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Entity
public class Director extends Human{

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY,
            mappedBy = "directors"
    )
    private List<Movie> movies = new ArrayList<>();

}

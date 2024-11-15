package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
public class Actor extends Human{

    @Formula(value="DATE_FORMAT(FROM_DAYS(DATEDIFF(NOW(), birthday)), '%y') +0")
    private Integer age;

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

}

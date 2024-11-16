package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@Entity
public class MovieDetails extends BaseEntity{

    @Column
    private String details;

    @JsonIgnore
    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "movie_id")
    private Movie movie;

}

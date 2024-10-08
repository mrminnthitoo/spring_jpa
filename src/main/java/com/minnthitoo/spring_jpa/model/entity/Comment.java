package com.minnthitoo.spring_jpa.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@Slf4j
@EqualsAndHashCode(callSuper=true)
public class Comment extends BaseEntity {

    @Column
    private String comment;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "movie_id")
    Movie movie;
}

package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.model.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Optional<Movie> getMovieById(Long movieId);
}

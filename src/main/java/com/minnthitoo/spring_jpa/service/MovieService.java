package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.model.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> getAllMovies();
    List<MovieDto> getAllMoviesByTitle(String title);
    MovieDto saveMovie(MovieDto movieDto);
    Optional<MovieDto> getMovieById(Long id);
    MovieDto updateMovie(MovieDto movieDto);
    MovieDto deleteMovieById(Long movieId);
    MovieDto assignActorToMovie(Long movieId, Long actorId);

}

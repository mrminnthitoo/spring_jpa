package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.common.response.exception.NotFoundException;
import com.minnthitoo.spring_jpa.model.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieDto> getAllMovies();
    Optional<MovieDto> getMovieById(Long movieId);

    MovieDto createMovie(MovieDto movieDto);

    MovieDto updateMovie(MovieDto movieDto) throws NotFoundException;
    MovieDto deleteMovie(MovieDto movieDto) throws NotFoundException;

}

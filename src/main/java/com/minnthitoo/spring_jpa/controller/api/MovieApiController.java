package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.common.response.exception.BeanValidationException;
import com.minnthitoo.spring_jpa.common.response.exception.NotFoundException;
import com.minnthitoo.spring_jpa.model.dto.MovieDetailsDto;
import com.minnthitoo.spring_jpa.model.dto.MovieDto;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.repository.MovieRepository;
import com.minnthitoo.spring_jpa.service.MovieService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

//    @Autowired
//    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @GetMapping
    ResponseEntity<List<MovieDto>> getAllmovies(){
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    ResponseEntity<MovieDto> getMovieById(@PathVariable Long movieId) throws NotFoundException{
        Optional<MovieDto> result = this.movieService.getMovieById(movieId);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else {
            Map<String, String> error = new HashMap<>();
            error.put("movieId", "Movie id "+ movieId +" not found.");
            throw new NotFoundException( "Movie Not Found.",error);
        }
    }

    @PostMapping
    ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto, BindingResult result) throws BeanValidationException {
        if (!result.hasErrors()){
            MovieDto savedMovie = this.movieService.createMovie(movieDto);
            return ResponseEntity.status(201).body(savedMovie);
        }else {
            throw new BeanValidationException("Validation Fail.",result.getFieldErrors());
        }
    }

    @PutMapping("/{movieId}")
    ResponseEntity<MovieDto> updateMovieById(@PathVariable Long movieId, @Valid @RequestBody MovieDto movieDto, BindingResult result) throws BeanValidationException, NotFoundException {
        if (result.hasErrors()){
            throw new BeanValidationException("Validation fails.", result.getFieldErrors());
        }else {
            Optional<MovieDto> checkMovie = this.movieService.getMovieById(movieId);
            if (checkMovie.isPresent()){
                movieDto.setId(movieId);
                MovieDto updatedMovie = this.movieService.updateMovie(movieDto);
                return ResponseEntity.ok(updatedMovie);
            }else {
                Map<String, String> error = new HashMap<>();
                error.put("movieId", "Movie Id " + movieId + " not found.");
                throw new NotFoundException("Movie Not Found.", error);
            }
        }
    }

    @DeleteMapping("/{movieId}")
    ResponseEntity<MovieDto> deleteMovieById(@PathVariable Long movieId) throws NotFoundException {
        Optional<MovieDto> result = this.movieService.getMovieById(movieId);
        if (result.isPresent()){
            MovieDto deletedMovie = this.movieService.deleteMovie(result.get());
            return ResponseEntity.ok(deletedMovie);
        }else {
            Map<String, String> error = new HashMap<>();
            error.put("movieId", "Movie id " + movieId + " not found.");
            throw new NotFoundException("Movie Not Found", error);
        }
    }

}

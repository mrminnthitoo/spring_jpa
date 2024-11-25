package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.common.response.exception.BeanValidationException;
import com.minnthitoo.spring_jpa.common.response.exception.NotFoundException;
import com.minnthitoo.spring_jpa.model.dto.ActorDto;
import com.minnthitoo.spring_jpa.model.dto.MovieDto;
import com.minnthitoo.spring_jpa.service.ActorService;
import com.minnthitoo.spring_jpa.service.MovieService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

//    @Autowired
//    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @GetMapping
    ResponseEntity<List<MovieDto>> getAllmovies(){
        return ResponseEntity.ok(this.movieService.getAllMovies());
    }

    @GetMapping("/title")
    ResponseEntity<List<MovieDto>> getMovieByTitle(@RequestParam String title){
        log.error("query method");
        return ResponseEntity.ok(this.movieService.getMoviesByTitle(title));
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

    @PostMapping("{movieId}/assignment/actor/{actorId}")
    ResponseEntity<MovieDto> addActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) throws NotFoundException{
        Optional<MovieDto> movieResult = this.movieService.getMovieById(movieId);
        Optional<ActorDto> actorResult = this.actorService.getActorById(actorId);
        if (movieResult.isPresent() && actorResult.isPresent()){
            MovieDto movieDto = this.movieService.assignActorToMovie(movieId, actorId);
            return ResponseEntity.ok(movieDto);
        }else {
            Map<String, String> error = new HashMap<>();
        if (movieResult.isEmpty()){
            error.put("movieId", "Movie id " + movieId + " not found.");
        }
        if (actorResult.isEmpty()){
            error.put("actorId", "Actor id " + actorId + " not found.");
        }
            throw new NotFoundException("Not found.", error);
        }
    }

}

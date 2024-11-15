package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.controller.api.exception.BeanValidationException;
import com.minnthitoo.spring_jpa.controller.api.exception.DataNotFoundException;
import com.minnthitoo.spring_jpa.model.dto.ActorDto;
import com.minnthitoo.spring_jpa.model.dto.MovieDto;
import com.minnthitoo.spring_jpa.service.ActorService;
import com.minnthitoo.spring_jpa.service.MovieService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/movies")
public class MovieApi {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ActorService actorService;

    @GetMapping
    public Iterable<MovieDto> getMovies() {
        return this.movieService.getAllMovies();
    }
    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long movieId) throws DataNotFoundException {
        Optional<MovieDto> result = this.movieService.getMovieById(movieId);
        if (result.isPresent()){
            return ResponseEntity.ok(result.get());
        }else{
            throw new DataNotFoundException("Movie id " + movieId + " not found.");
        }
    }

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@Valid @RequestBody MovieDto movieDto, BindingResult result) throws BeanValidationException{
        if (!result.hasErrors()){
            MovieDto savedMovie = this.movieService.saveMovie(movieDto);
            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(savedMovie);
        }else{
            for (ObjectError error : result.getFieldErrors()){
                log.error(error.toString());
            }
            throw new BeanValidationException(result.getAllErrors());
        }
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDto> updateMovieById(@PathVariable Long movieId, @Valid @RequestBody MovieDto movieDto, BindingResult bindingResult) throws DataNotFoundException, BeanValidationException{
        Optional<MovieDto> result = this.movieService.getMovieById(movieId);
        if (!bindingResult.hasErrors()){

            if (result.isPresent()){
                movieDto.setId(movieId);
                MovieDto updatedMovie = this.movieService.updateMovie(movieDto);
                return ResponseEntity.of(Optional.of(updatedMovie));
            }else {
                throw new DataNotFoundException("Movie id " + movieId + " not found.");
            }

        }else{
            throw new BeanValidationException(bindingResult.getAllErrors());
        }
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<MovieDto> deleteMovieById(@PathVariable Long movieId) throws DataNotFoundException{
        Optional<MovieDto> result = this.movieService.getMovieById(movieId);
        if (result.isPresent()){
            MovieDto deletedMovie = this.movieService.deleteMovieById(movieId);
            return ResponseEntity.of(Optional.of(deletedMovie));
        }else {
            throw new DataNotFoundException("Movie id " + movieId + " not found.");
        }
    }

    @PostMapping("/{movieId}/assignment/actors/{actorId}")
    public ResponseEntity<MovieDto> assignActorToMovie(@PathVariable Long movieId, @PathVariable Long actorId) throws DataNotFoundException{
        Optional<MovieDto> movieResult = this.movieService.getMovieById(movieId);
        Optional<ActorDto> actorResult = this.actorService.getActorById(actorId);
        if (movieResult.isPresent() && actorResult.isPresent()){
            MovieDto movieDto = this.movieService.assignActorToMovie(movieId, actorId);
            return ResponseEntity.ok(movieDto);
        }else {
            throw new DataNotFoundException("Movie " + movieId + " or Actor " + actorId + " not found.");
        }
    }

    @GetMapping("/title")
    public ResponseEntity<List<MovieDto>> findMovieByTitle(@PathParam("title") String title){
        log.info("find by title " + title);
        List<MovieDto> movies = this.movieService.getAllMoviesByTitle(title);
        return ResponseEntity.ok(movies);
    }
}

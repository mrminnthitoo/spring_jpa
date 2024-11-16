package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieApiController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    List<Movie> getAllmovies(){
        return this.movieRepository.findAll();
    }

    @GetMapping("/{movieId}")
    Optional<Movie> getMovieById(@PathVariable Long movieId){
        return this.movieRepository.findById(movieId);
    }

}

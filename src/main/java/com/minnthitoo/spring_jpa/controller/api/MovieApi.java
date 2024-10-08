package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.dao.MovieDao;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/movies")
public class MovieApi {
    @Autowired
    MovieDao movieDao;

    @GetMapping
    public Iterable<Movie> getMovies() {
        return this.movieDao.findAll();
    }

    @GetMapping("/{movieId}")
    public Optional<Movie> getMovieById(@PathVariable Long movieId) {
        log.info("Get movie by id: {}", movieId);
        if (this.movieDao.findById(movieId).isPresent()) {
            log.info("Get movie by id: {}", movieId);
            log.info("Movie {}", this.movieDao.findById(movieId).get());
        }else{
            log.info("no movie");
        }
         return this.movieDao.findById(movieId);
    }
}

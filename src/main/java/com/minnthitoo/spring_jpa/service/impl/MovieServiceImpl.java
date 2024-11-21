package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.repository.MovieRepository;
import com.minnthitoo.spring_jpa.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    @Override
    public Optional<Movie> getMovieById(Long movieId) {
        Optional<Movie> result = this.movieRepository.findById(movieId);
        List<Actor> actors = result.get().getActors();
        for (Actor actor : actors){
            log.info("Actor {}", actor);
        }
        return result;
    }
}

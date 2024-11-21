package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieQBETest {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    @Test
    public void testMovieQBE(){
        Movie movie = new Movie();
        movie.setGenre("Drama");

        ExampleMatcher matcher = ExampleMatcher.matching();
//                .withIgnorePaths("genre");
        Example<Movie> example = Example.of(movie, matcher);
        List<Movie> movies = this.movieRepository.findAll(example);
        movies.forEach(each->{
            log.info("{}", each);
        });

    }

    @Transactional
    @Test
    public void testMovieQBEGetMovieWithTitle(){
        Movie movie = new Movie();
        movie.setTitle("Movie");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::startsWith);
//                .withIgnorePaths("genre");
        Example<Movie> example = Example.of(movie, matcher);
        List<Movie> movies = this.movieRepository.findAll(example);
        movies.forEach(each->{
            log.info("{}", each);
        });

    }

}

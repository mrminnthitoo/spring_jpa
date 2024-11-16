package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void insertMovie(){
        Movie movie = new Movie();
        movie.setTitle("Titanic");
        movie.setYear(1998L);
        movie.setGenre("Drama");
        this.movieRepository.save(movie);
    }

    @Test
    public void getAllMoviesTest(){
        List<Movie> movies = this.movieRepository.findAll();
        for(Movie movie : movies){
            log.info("{}", movie);
        }
    }

}

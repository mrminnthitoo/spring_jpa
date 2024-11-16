package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.model.entity.MovieDetails;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    @Test
    public void insertMovie(){
        Movie movie = new Movie();
        movie.setTitle("Titanic");
        movie.setYear(1998L);
        movie.setGenre("Drama");

        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setDetails("Titanic Movie Details");

        movie.setMovieDetails(movieDetails);
        movieDetails.setMovie(movie);

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

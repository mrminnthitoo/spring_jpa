package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.model.entity.MovieDetails;
import com.minnthitoo.spring_jpa.model.entity.enums.Gender;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        movie.setTitle("Test");
        movie.setYear(1998L);
        movie.setGenre("Action");

        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setDetails("Test Details");

        movie.setMovieDetails(movieDetails);
        movieDetails.setMovie(movie);

        this.movieRepository.save(movie);
    }

    @Transactional
    @Test
    public void addActorsToMovie(){
        Movie movie = this.movieRepository.findById(1L).get();

        Actor actor1 = new Actor();
        actor1.setFirstName("Leonado");
        actor1.setLastName("Dicaprao");
        actor1.setGender(Gender.MALE);
        actor1.setBirthday(new Date());

        Actor actor2 = new Actor();
        actor2.setFirstName("Actor");
        actor2.setLastName("2");
        actor2.setGender(Gender.FEMALE);
        actor2.setBirthday(new Date());

        movie.getActors().add(actor1);
        actor1.getMovies().add(movie);

        movie.getActors().add(actor2);
        actor2.getMovies().add(movie);

        this.movieRepository.save(movie);

    }

    @Test
    public void getAllMoviesTest(){
        List<Movie> movies = this.movieRepository.findAll();
        for(Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void lazyLoadingTest(){
        Optional<Movie> result = this.movieRepository.findById(1L);
        Movie movie = result.get();

        log.info("Title -> {}", movie.getTitle());
        Set<Actor> actors = movie.getActors();
        log.info("Actors -> {}", actors);
    }

    @Transactional
    @Test
    public void getAllMoviesNativeTest(){
        Movie movie = this.movieRepository.getAllMoviesNative("Titanic");
        log.info("{}", movie);
    }

}

package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.*;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
public class MovieDaoTest {

    @Autowired
    private MovieDao movieDao;

    @Autowired MovieDetailsDao movieDetailsDao;

    /*
    @Test
    void insertMovie(){
        Movie movie = new Movie();
        movie.setTitle("Movie 2");
        movie.setGenre("Action");
        movie.setYear(2017);

        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setDetails("Movie 2 Details");

        movie.setMovieDetails(movieDetails);
        movieDetails.setMovie(movie);

        movieDao.save(movie);
//        movieDetailsDao.save(movieDetails);

    }

     */



    /*
    @Test
    void getAllMovies(){
        Iterable<Movie> movies = this.movieDao.findAll();
        for(Movie movie : movies){
            log.info(movie.toString());
        }
    }

     */

    /*
    @Test
    void insertMovieWithActor(){
        Optional<Movie> movie = movieDao.findById(1L);
        Actor actor1 = new Actor();
        Name name = new Name();
        name.setFirstName("Actor");
        name.setLastName("One");
        actor1.setName(name);

        actor1.setBirthday(new Date());
        actor1.setGender(Gender.MALE);

        List<Actor> actors = new ArrayList<>();
        actors.add(actor1);

        Actor actor2 = new Actor();
        Name name2 = new Name();
        name2.setFirstName("Actor");
        name2.setLastName("Two");
        actor2.setName(name2);

        actor2.setBirthday(new Date());
        actor2.setGender(Gender.FEMALE);

        actors.add(actor2);

        movie.get().setActors(actors);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie.get());

        actor1.setMovies(movies);
        actor2.setMovies(movies);

        movieDao.save(movie.get());
//        movieDetailsDao.save(movieDetails);

    }

     */

    @Test
    @Transactional
    void lazyLoadingTest(){
        Optional<Movie> result = movieDao.findById(1L);
        Movie movie = result.get();

        log.info("Movie Title : {}", movie.getTitle());

        List<Actor> actors = movie.getActors();

        for (Actor actor : actors) {
            log.info("Actor : {}", actor);
        }
    }

    // insert test
    @Test
    @Transactional
    void saveTwoMoviesTest(){
        int insertTwoMovies = this.movieDao.insertTwoMovies("Movie1", "Movie2");
        log.info("Insert two movies : {}", insertTwoMovies);
    }

}

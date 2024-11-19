package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.dto.TitleAndGenre;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieHQLTest {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    @Test
    public void testGetAllMovie(){
        List<Movie> movies = this.movieRepository.getAllMovie();
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Test
    public void testProjection(){
        List<TitleAndGenre> titleAndGenres = this.movieRepository.getAllTitleAndGenre();
        for (TitleAndGenre titleAndGenre : titleAndGenres){
            log.info("{}, {}", titleAndGenre.getTitle(), titleAndGenre.getGenre());
        }
    }

    @Test
    public void testGetAllGenre(){
        List<String> genres = this.movieRepository.getAllGenre();
        genres.forEach(log::info);
    }

    @Transactional
    @Test
    public void testGetAllMovieLike(){
        List<Movie> movies = this.movieRepository.getAllMovieLike("Titanic");
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Test
    public void testFindTotalMovieByGenres(){
        int count = this.movieRepository.findTotalMovieByGenres("Drama");
        log.info("Count {}", count);
    }

    @Test
    public void testGetAllMovieWithYearGTE(){
        List<Movie> movies = this.movieRepository.getAllMovieWithYearGTE(2010L);
        log.info("Movie Count {}", movies.size());
    }

    @Test
    public void testGetAllMovieWithYearAndGenre(){
        List<Movie> movies = this.movieRepository.getAllMovieWithYearAndGenre(2010L, "Action");
        log.info("Movie count {}", movies.size());
    }

    @Test
    public void testGetAllMoviesBetween(){
        List<Movie> movies = this.movieRepository.getAllMoviesBetween(2010L, 2020L);
        log.info("Movie count {}", movies.size());
    }

    @Transactional
    @Test
    public void testGetAllMoviesWithActorIn(){
        List<Movie> movies = this.movieRepository.getAllMoviesWithActorIn("Actor", "1");
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMoviesWithComments(){
        List<Movie> movies = this.movieRepository.getMoviesWithComments();
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMoviesWithCommentsLeftJoin(){
        List<Movie> movies = this.movieRepository.getMoviesWithCommentsLeftJoin();
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

}

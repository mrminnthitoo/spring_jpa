package com.minnthitoo.spring_jpa.repository.specification;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieSpecificationTest {

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    @Test
    public void testGetMovieSpecByYear(){
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieByYear(2010L));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMovieSpecByTitleAndYear(){
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieByTitleOrYear(null, 2010L));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMovieSpecByYearGreaterThan(){
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieByYearGreaterThan(2010L));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMovieSpecByYearBetween(){
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieByYearBetween(2010L, 2020L));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMovieByTitleLike(){
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieByTitleLike("Test"));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMovieWithActorIn(){
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieWhereActorIn("Leonado Dicaprao"));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMovieSepcByGenre(){
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieGroupByGenre("Drama"));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    @Transactional
    @Test
    public void testGetMovieSepcInGenres(){
        List<String> genres = new ArrayList<>();
        genres.add("Drama");
        genres.add("Sci-Fi");
        List<Movie> movies = this.movieRepository.findAll(MovieSpecification.getMovieInGenre(genres));
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }


}

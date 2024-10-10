package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.dto.GenreCountDto;
import com.minnthitoo.spring_jpa.model.dto.TitleAndGenre;
import com.minnthitoo.spring_jpa.model.entity.Book;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Hql {
    @Autowired
    BookDao bookDao;

    @Autowired
    MovieDao movieDao;

    @Autowired
    ActorDao actorDao;

    @Test
    public void test() {
        Book book = this.bookDao.findByTitle("Title 1");
        log.info(book + "");
    }

    @Test
    public void nativeQueryTest(){
        Book book = this.bookDao.findByTitleNative("Title 1");
        log.info(book + "");
    }

    @Test
    @Transactional
    public void nativeQueryWithAssociationTest(){

        Movie movie = movieDao.findByTitleNative("Movie 1");
        log.info(movie + "");

        log.info("Number of actors " + movie.getActors().size());

    }

    @Test
    @Transactional
    public void selectHqlTest(){
        List<Movie> movies = this.movieDao.hqlGetAllMovies();
        for (Movie movie : movies) {
            log.info(movie + "");
        }
    }

    @Test
    @Transactional
    public void selectWithProjectiontest(){
        List<TitleAndGenre> movies = this.movieDao.hqlGetAllMoviesWithProjection();
        for (TitleAndGenre movie : movies) {
            log.info(movie.getTitle() + " " + movie.getGenre());
        }
    }

    @Test
    @Transactional
    public void getGenreCountTest(){
        List<GenreCountDto> genreCountDtos = this.movieDao.getGenreCount();
        for (GenreCountDto genreCountDto : genreCountDtos) {
            log.info(genreCountDto.getGenre() + "");
            log.info(genreCountDto.getCount() + "");
            log.info(genreCountDto.getGenreUpperCase());
        }
    }

    // get movie with like
    @Test
    @Transactional
    public void getMovieWithTitleTest(){
        List<Movie> movies = this.movieDao.getMovieByTitle("%Movie%");
        for (Movie movie : movies) {
            log.info(movie + "");
        }
    }

    // get genre count by genre name
    @Test
    @Transactional
    public void getGenreCountTestWithNamedQuery(){
        int count = this.movieDao.getGenreCountByGenre("Action");
        log.info("Genre count {}", count);
    }

    // get movies by year
    @Test
    @Transactional
    public void getMoviesGTETest(){
        List<Movie> movies = this.movieDao.getMovieByYear(2015L);
        for (Movie movie : movies) {
            log.info(movie + "");
        }
    }

    // get movies by year and genre
    @Test
    @Transactional
    public void getMoviesByYearGTEAndGenreTest(){
        List<Movie> movies = this.movieDao.getMoviesByYearGTEAndGenre(2015L, "Drama");
        for (Movie movie : movies){
            log.info(movie + "");
        }
    }

    @Test
    @Transactional
    public void getBirthdayTest(){
        List<Integer> years = this.actorDao.getYears();
        for (Integer year : years) {
            log.info(year + "");
        }
    }

    // get movie between years
    @Test
    @Transactional
    public void getMoviesBetweenYearsTest(){
        List<Movie> movies = this.movieDao.getMoviesByYearBetween(2010L, 2020L);
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    // get movie with actor first name and last name
    @Test
    @Transactional
    public void getMoviesByFirstAndLastNameTest(){
        List<Movie> movies = this.movieDao.getMoviesByFirstAndLastName("Actor", "One");
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    // get movie by comments
    @Test
    @Transactional
    public void getMoviesByComments(){
        List<Movie> movies = this.movieDao.getMoviesByComments();
        for (Movie movie : movies){
            log.info("{}", movie);
        }
    }

    // get movie by comments left join
    @Test
    @Transactional
    public void getMovieByCommentsLeftJoinTest(){
        List<Movie> movies = this.movieDao.getMovieByCommentsLeftJoin();
        for(Movie movie : movies){
            log.info("{}", movie);
        }
    }

}

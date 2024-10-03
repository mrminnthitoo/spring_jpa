package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieDaoTest {

    @Autowired
    private MovieDao movieDao;

    @Test
    void insertMovie(){
        Movie movie = new Movie();
        movie.setTitle("Movie 1");
        movie.setGenre("Action");
        movie.setYear(2017);

        movieDao.save(movie);

    }
}

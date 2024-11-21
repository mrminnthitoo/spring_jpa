package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void testLazyLoading(){
        this.movieService.getMovieById(1L);
    }

}

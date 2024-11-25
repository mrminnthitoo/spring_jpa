package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.common.response.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

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

    @Transactional
    @Test
    public void testAddActorToMovie(){
        try {
            this.movieService.assignActorToMovie(11L, 3L);
            this.movieService.assignActorToMovie(11L, 4L);
            this.movieService.assignActorToMovie(11L, 5L);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

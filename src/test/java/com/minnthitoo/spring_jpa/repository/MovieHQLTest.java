package com.minnthitoo.spring_jpa.repository;

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

}

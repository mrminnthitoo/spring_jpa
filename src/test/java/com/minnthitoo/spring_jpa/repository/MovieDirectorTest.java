package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Director;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.model.entity.enums.Gender;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(value = false)
public class MovieDirectorTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @Transactional
    public void insertDirectorToMovie(){
        Movie movie = this.movieRepository.findById(1L).get();

        Director d1 = new Director();
        d1.setFirstName("Director");
        d1.setLastName("1");
        d1.setBirthday(new Date());
        d1.setGender(Gender.MALE);

        movie.getDirectors().add(d1);
        d1.getMovies().add(movie);

        this.movieRepository.save(movie);
    }

}
